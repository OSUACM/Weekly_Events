#! /usr/bin/env python3

import sys
import re
import textwrap
import datetime
import pathlib

INDENT_WIDTH = 4

def print_item(content, indent):
    sys.stdout.write('%s* %s\n' % (' ' * INDENT_WIDTH * indent, content))

def print_link(path, title, indent):
    print_item('[%s](%s)' % (title, '/'.join(path.parts)), indent)

def process_file_item(path, used_files, name, title):
    with path / name as file_item:
        assert file_item.exists()
        assert file_item.is_file()

        used_files.append(name)

        print_link(file_item, title, 2)

def process_readme(path, used_files, time_str):
    with path / 'README.md' as readme:
        assert readme.exists()
        assert readme.is_file()

        used_files.append('README.md')

        lines = readme.open().read().splitlines()
        assert len(lines) >= 2
        assert lines[0].endswith(' - ' + time_str)
        assert lines[1] == '==='

        last = None
        section = None

        for line in lines:
            if line == '---':
                assert last is not None

                print_item(last, 1)
                section = last
            elif re.match(r'^\[.*\]\((?!\w+://).*\)$', line):
                assert section is not None

                match = re.match(r'^\[(.+)\]\((.+)\)$', line)
                assert match is not None

                process_file_item(
                    path,
                    used_files,
                    match.group(2),
                    match.group(1)
                )

            last = line

def process_slides(path, used_files):
    with path / 'SLIDES.pdf' as slides:
        if slides.exists():
            assert slides.is_file()

            used_files.append('SLIDES.pdf')

            print_link(slides, 'Slides', 2)

def process_dir(path):
    if path.match('????-??-??'):
        assert path.is_dir()

        time = datetime.datetime.strptime(path.name, '%Y-%m-%d')
        time_str = time.strftime('%b %d, %Y')
        print_link(path, time_str, 0)

        used_files = []

        process_readme(path, used_files, time_str)
        process_slides(path, used_files)

        assert sorted(used_files) == sorted([
            file_item.name for file_item in path.iterdir()
        ])

def process_all(root):
    for path in sorted(root.iterdir()):
        process_dir(path)

if __name__ == '__main__':
    sys.stdout.write(textwrap.dedent('''\
        Weekly Events
        ===

        This repository contains slides / outline documents of OSU ACM Club.

        Table of Contents
        ---

    '''))

    process_all(pathlib.Path('.'))

    sys.stdout.write(textwrap.dedent('''
        Contribution
        ---

        If you are interested in doing a presentation in our weekly events, please upload slides or an outline document by pushing a commit or opening a pull request.

        PDF and markdown are suggested formats.
    '''))
