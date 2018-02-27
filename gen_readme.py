#! /usr/bin/env python3

from textwrap import dedent
from pathlib import Path

INDENT_WIDTH = 4

def print_path(path, indent):
    print(' ' * indent * INDENT_WIDTH, end='')
    print('* [%s](%s)' % (path.name, '/'.join(path.parts)))

def print_dir(path, indent):
    for sub_path in sorted(path.iterdir()):
        if not sub_path.match('README.md'):
            if sub_path.is_dir():
                print_path(sub_path, indent)
                print_dir(sub_path, indent + 1)
            elif sub_path.is_file():
                print_path(sub_path, indent)

if __name__ == '__main__':
    print(dedent('''\
        Weekly Events
        ===

        This repository contains slides / outline documents of OSU ACM Club.

        Table of Contents
        ---
    '''))

    for path in sorted(Path('.').iterdir()):
        if path.is_dir() and path.match('????-??-??'):
            print_path(path, 0)
            print_dir(path, 1)

    print(dedent('''
        Contribution
        ---

        If you are interested in doing a presentation in our weekly events, please upload slides or an outline document by pushing a commit or opening a pull request.

        PDF and markdown are suggested formats.
    '''), end='')
