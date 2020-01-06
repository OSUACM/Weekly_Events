#! /usr/bin/env python3

import sys
import re
import textwrap
import datetime
import pathlib

INDENT_WIDTH = 4


def print_item(content, indent):
    f.write('%s* %s\n' % (' ' * INDENT_WIDTH * indent, content))


def print_link(path, title, indent):
    print_item('[%s](%s)' % (title, '/'.join(path.parts)), indent)


def process_file_item(path, used_files, name, title):
    with path / name as file_item:
        assert file_item.exists(), '%s does not exist' % str(file_item)
        assert file_item.is_file(), '%s is not a file' % str(file_item)

        used_files.append(name)

        print_link(file_item, title, 2)


def process_readme(path, used_files, time_str):
    with path / 'README.md' as readme:
        assert readme.exists(), '%s does not exist' % str(readme)
        assert readme.is_file(), '%s is not a file' % str(readme)

        used_files.append('README.md')

        lines = readme.open().read().splitlines()
        assert len(lines) >= 2, '%s is too short' % str(readme)

        # title

        assert lines[1] == '===', 'title not found %s: %s' % (
            str(readme), lines[1])

        title_match = re.fullmatch(r'(.+) - ' + time_str, lines[0])
        assert title_match is not None, 'title syntax error %s: %s' % (
            str(readme), lines[0])

        last = None
        section = None

        for line in lines:
            if line == '---':
                # new section

                assert last is not None, 'section not found %s: %s' % (
                    str(readme), line)

                section_match = re.fullmatch(r'(.+) - (.+)', last)
                assert section_match is not None, 'section syntax error %s: %s' % (
                    str(readme), last)

                print_item(last, 1)
                section = section_match.group(2)
            elif re.fullmatch(r'\[.*\]\((?!\w+://).*\)', line):
                # new link

                assert section is not None, 'link in unknown section %s: %s' % (
                    str(readme), line)

                link_match = re.fullmatch(r'\[(.+)\]\((.+)\)', line)
                assert link_match is not None, 'link syntax error %s: %s' % (
                    str(readme), line)

                # assert link_match.group(2).startswith(section.replace(' ', '-')), 'link file name error %s: %s' % (str(readme), line)

                process_file_item(
                    path,
                    used_files,
                    link_match.group(2),
                    link_match.group(1)
                )

            last = line


def process_slides(path, used_files):
    with path / 'SLIDES.pdf' as slides:
        if slides.exists():
            assert slides.is_file(), '%s is not a file' % str(slides)

            used_files.append('SLIDES.pdf')

            print_link(slides, 'Slides', 1)


def write_header():
    f.write(textwrap.dedent('''\
        Table of Contents
        ---
'''))


def write_footer():
    pass

def process_dir(path):
    if path.match('????-??-??'):
        assert path.is_dir(), '%s is not a dir' % str(path)

        time = datetime.datetime.strptime(path.name, '%Y-%m-%d')
        time_str = time.strftime('%b %d, %Y')
        print_link(path, time_str, 0)

        used_files = []

        process_readme(path, used_files, time_str)
        process_slides(path, used_files)

        assert sorted(used_files) == sorted([
            file_item.name for file_item in path.iterdir()
        ]), '%s contains unused file' % str(path)


def process_all(root):
    for path in sorted(root.iterdir()):
        process_dir(path)


if __name__ == '__main__':
    f = open("README.md", "w")
    write_header()
    process_all(pathlib.Path('.'))
    write_footer()
