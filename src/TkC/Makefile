# Makefile for TrackController Module

############### GENERAL VARS #################
CC             := javac
RUNCMD         := java
JARCMD         := jar -cvf
MKDIR_P        := mkdir -p
RM             := rm -f
ifdef OS # windows
	JAVAFX_PATH    = ../../lib/javafx-sdk-11.0.2-windows/lib
else
	JAVAFX_PATH    = ../../lib/javafx-sdk-11.0.2-unix/lib
endif 


JAVAFX_MODULES   = javafx.controls# NOTE: must be comma separated
JFLAGS          := --module-path=$(JAVAFX_PATH) --add-modules $(JAVAFX_MODULES)
BLOCKFILES       = Block SwitchBlock CrossingBlock StationBlock BlockType
BLOCK_SRCDIR    := ../
BLOCK_JAVAFILES := $(addsuffix .java,$(addprefix $(BLOCK_SRCDIR),$(BLOCKFILES)))
CLASS_PATH      := ../../bin/TkC

######### TRACK CONTROLLER #############
TKC_MODULE     = TrackController TrackControllerMain TrackControllerGUI
TKC_SRCDIR     = ../../src/TkC/
TKC_BINDIR     = ../../bin/

TKC_JAVAFILES  = $(addsuffix .java,$(addprefix $(TKC_SRCDIR),$(TKC_MODULE)))
TKC_CLASSFILES = $(addsuffix .class,$(addprefix $(TKC_BINDIR),$(TKC_MODULE)))

#JAVAFILES      := $(addsuffix .java,$(MODULES))
#CLASSFILES     = $(addsuffix .class,$(MODULES))

################# TEST VARS ##################

TTARGET_CLASS  := $(addprefix $(TKC_BINDIR),TrackControllerGUI.class)
TTARGET        := TrackControllerGUI
.PHONY         := clean runtest

# Rules
default: $(TTARGET_CLASS)

run: $(TTARGET_CLASS)
	$(RUNCMD) $(JFLAGS) -cp $(TKC_BINDIR) $(TTARGET)


$(TTARGET_CLASS): $(TKC_JAVAFILES) $(BLOCK_JAVAFILES)
	$(CC) $(JFLAGS) -d $(TKC_BINDIR) $(BLOCK_JAVAFILES) $(TKC_JAVAFILES)

clean:
	$(RM) '*.class' *.class $(TKC_CLASSFILES)
