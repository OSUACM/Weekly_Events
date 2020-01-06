'''
taken from
https://effectivepython.com/2015/03/10/consider-coroutines-to-run-many-functions-concurrently/
'''

from collections import namedtuple

ALIVE = 'o'
EMPTY = '.'

Query = namedtuple('Query', ('y', 'x'))
Transition = namedtuple('Transition', ('y', 'x', 'state'))
END_GENERATION = object()


def game_logic(current_state, living_neighbors: int):
    if current_state == ALIVE:
        if living_neighbors < 2:
            return EMPTY  # Die: Too few
        elif living_neighbors > 3:
            return EMPTY  # Die: Too many
    else:
        if living_neighbors == 3:
            return ALIVE  # Regenerate
    return current_state


def count_neighbors(y, x):
    # call this, then send in 8 states as 8 queries are sent out.
    # returned is the number of living neighbors
    n_ = yield Query(y + 1, x + 0)
    ne = yield Query(y + 1, x + 1)
    e_ = yield Query(y + 0, x + 1)
    se = yield Query(y - 1, x + 1)
    s_ = yield Query(y - 1, x + 0)
    w_ = yield Query(y + 0, x - 1)
    sw = yield Query(y - 1, x - 1)
    nw = yield Query(y + 1, x - 1)

    neighbor_states = [n_, ne, e_, se, s_, sw, w_, nw]
    return sum(1 for state in neighbor_states if state == ALIVE)


def step_cell(y, x):
    # yield items into live_a_generation.
    # recieves responses from live_a_generation.
    state = yield Query(y, x)
    neighbors = yield from count_neighbors(y, x)
    next_state = game_logic(state, neighbors)
    yield Transition(y, x, next_state)


def request_aggregator(height, width):
    # yield all of the required queries, send the responses
    # Simply aggregates all of the individual query streams from step_cell calls
    while True:
        for y in range(height):
            for x in range(width):
                yield from step_cell(y, x)
        yield END_GENERATION


class Grid(object):
    def __init__(self, height, width):
        self.height = height
        self.width = width
        self.rows = []
        for _ in range(self.height):
            self.rows.append([EMPTY] * self.width)

    def __str__(self):
        return '\n'.join(''.join(cell for cell in row)
                         for row in self.rows)

    def query(self, y, x):
        return self.rows[y % self.height][x % self.width]

    def assign(self, y, x, state):
        self.rows[y % self.height][x % self.width] = state


def next_generation_grid(grid, sim):
    # think of this as a receiver that follows the instructions of step_cell calls
    next_generation = Grid(grid.height, grid.width)
    request = next(sim)
    while request is not END_GENERATION:
        if isinstance(request, Query):
            state = grid.query(request.y, request.x)
            # answer the previous query request, then wait for the next one
            request = sim.send(state)
        elif isinstance(request, Transition):
            # do the transition requested
            next_generation.assign(request.y, request.x, request.state)
            # wait for the next request
            request = next(sim)
    return next_generation


class ColumnPrinter(object):

    def __init__(self):
        self.columns = []

    def append(self, s: str):
        if len(self.columns) == 8:
            print(self)
            self.columns = [s]
        else:
            self.columns.append(s)

    def __str__(self):
        # width = self.columns[0].index('\n')
        # format = '{: ^' + str(width) + '}'
        # header = ' | '.join(format.format(i) for i in range(len(self.columns))) + '\n'
        return '\n' + '\n'.join(' | '.join(row) for row in
                                zip(*(col.splitlines() for col in self.columns)))


if __name__ == '__main__':
    grid = Grid(8, 8)
    grid.assign(0, 3, ALIVE)
    grid.assign(1, 4, ALIVE)
    grid.assign(2, 2, ALIVE)
    grid.assign(2, 3, ALIVE)
    grid.assign(2, 4, ALIVE)
    columns = ColumnPrinter()
    sim = request_aggregator(grid.height, grid.width)
    columns.append(str(grid))
    for i in range(200):
        grid = next_generation_grid(grid, sim)
        columns.append(str(grid))
