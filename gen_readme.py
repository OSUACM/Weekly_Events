#! /usr/bin/env python3

from pathlib import Path

IDENT_W = 4

DIR_BIT = 1
FILE_BIT = 2

def print_path(p, ident):
    print(" " * ident * IDENT_W, "- ", end="")
    print("[%s](%s)" % (p.name, "/".join(p.parts)))

def print_dir(d, ident = 0, flags=DIR_BIT|FILE_BIT):
    for c in sorted(d.iterdir()):
        if c.match(".*"):
            continue
        if c.is_dir() and flags & DIR_BIT:
            print_path(c, ident)
            print_dir(c, ident+1)
        elif c.is_file() and flags & FILE_BIT:
            print_path(c, ident)

if __name__ == "__main__":
    print(
"""
Weekly Events
===

This repository contains slides / outline documents of OSU ACM Club.

Table of Contents
---
"""
)

    print_dir(Path("./"), flags=DIR_BIT)

    print(
"""
Contribution
---

If you are interested in doing a presentation in our weekly events, please upload slides or an outline document by pushing a commit or opening a pull request.

PDF and markdown are suggested formats.
"""
    )
