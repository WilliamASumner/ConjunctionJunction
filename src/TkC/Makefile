# Makefile for team Conjunction Junction

############### GENERAL VARS #################
CC             := javac
RUNCMD         := java
JARCMD         := jar -cvf
MKDIR_P        = mkdir -p
ifdef OS # windows
	JAVAFX_PATH    = ../../lib/javafx-sdk-11.0.2-windows/lib
else
	JAVAFX_PATH    = ../../lib/javafx-sdk-11.0.2-unix/lib
endif 


JAVAFX_MODULES = javafx.controls # NOTE: must be comma separated
JFLAGS         := --module-path=$(JAVAFX_PATH) --add-modules $(JAVAFX_MODULES)
BLOCKFILES     = Block SwitchBlock CrossingBlock StationBlock BlockType

############### MODULE VARS ##################
MODULES        = TkC TkM TrC TrM CTC

######### TRACK CONTROLLER #############
TKC_MODULE     = TrackController TrackControllerMain TrackControllerGUI
TKC_SRCDIR        = ./src/TkC/ # TODO ADD curr_dir variable
TKC_BINDIR        = ./bin/TkC/

TKC_JAVAFILES  = $(addsuffix .java,$(TKC_MODULE))
TKC_CLASSFILES = $(addsuffix .class,$(TKC_MODULE))

TKC_JAVAFILES  = $(addprefix $(TKC_SRCDIR),$(TKC_JAVAFILES)) # dir prefixes
TKC_CLASSFILES = $(addprefix $(TKC_BINDIR),$(TKC_CLASSFILES))

#JAVAFILES      := $(addsuffix .java,$(MODULES))
#CLASSFILES     = $(addsuffix .class,$(MODULES))

################# TEST VARS ##################

TTARGET        := TrainControllerGUI.class
.PHONY         := clean runtest

RM = rm -f
MKDIR_P = mkdir -p

# Rules
runtest: $(TTARGET)
	$(RUNCMD) $(JFLAGS) $(TTARGET)

$(TTARGET): $(TKC_CLASSFILES) $(TKC_JAVAFILES) $(BLOCKfILES)
	$(CC) $(JFLAGS) -d $(TKC_BINDIR) $(TKC_JAVAFILES) $(BLOCKFILES)

clean:
	$(RM) '*.class' *.class
