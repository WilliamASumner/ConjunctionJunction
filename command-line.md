# How to use the commandline (Cygwin)

## Format
- For `code` examples, an `<option>` indicates a spot that should be filled in with a desired value within the `<>`'s (i.e. `<number>` can be replaced with a `1`)

## Helpful bash commands
- `ls` - lists directories (`ls -a` lists all files, even hidden ones)
- `pwd` - print working directory
- `cd <directory path>` - change the directory (using just `cd` will take you to your home directory)
- `rm <files>` - remove files from the directory (*_warning:_* this is a dangerous command as files cannot be restored once they are `rm`ed!)
- `mv <file to move> <directory>` or `mv <file to move> <directory/newfile>` - moves a file and/or renames it
- `cp <file to copy> <new filename>` - copies a file

## Helpful git commands
- `git checkout --track origin/<branch name>` - checks out and switches to `<branch name>` as it was pulled from the github repo (i.e. the branch as it appears on github)
- `git checkout <branch name>` - switch to a branch named `<branch name>`
- `git add <file>` - adds a file to the current commits
- `git remove <file>` - removes a file to the current commits
- `git commit -m "<commit message string>"` - commits the changes you added/rmed to the current commit 
- `git pull` - pulls code from the github repo to your local machine, be careful as this will try to merge changes with your current directory. Make sure all changes are commited before `git pull`ing.
- `git push` - pushes changes to the github repo, make sure you pull before you push so that conflicts are resolved on your machine.
- `git checkout -b <branch name>` - creates a new branch and switches to it
- `git branch` - lists all branches and the current is highlighted
- `git status` - shows you what is going to be in the current commit and also gives hints about how to undo changes
- `git merge <branch name>`  - merges a branch `<branch name>` to the current branch, conflicts can arise here and must be dealt with
- `git diff` - shows the changes you've made in the current commit-inprogress.


### Dealing with Merge Conflicts
- Basic format
```
<<<<<<< HEAD
File 1's version of the changed line
=======
File 2's version of the changed line
>>>>>>> <commit or branch with conflict>
```
- To resolve this, basically choose one version or merge changes together to create the desired code, add/commit the change and push.

## Make
- running `make` in a directory with makefile will cause it to execute the first valid target it can find (i.e. lines begining with `<target-name>: dependencies`)

- running `make <target-name>` will cause that target and any depedencies to run
### Basic syntax
Makefiles are made up of targets with dependencies and commands to run for those targets. They have the foltlowing structure:
```
<target>:<dependency> ... <dependency>
    Command to run
```
Targets can either be real filenames or dummy names if defined in `.PHONY`. Make starts by reading either the given target (if using `make <target-name>`) or the first target that is listed in the file. It is important not that the command(s) under the `<target>:<dependency>...<dependency>` *MUST* be indented with a single tab. Anything else and make will complain.

### Variables
Variables in make can be defined with `SOMEVAR = <string>`. They can also be defined with the `:=` which is typically used for values that won't change throughout the makefile such as the compiler (e.g. `CC := gcc`) etc. Generally these are useful for building up more complicated commands with long flag variables or many files. Checkout the `Makefile` in the top-level directory of this repo.
