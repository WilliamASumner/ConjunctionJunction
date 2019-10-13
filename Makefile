# Makefile for team Conjunction Junction

############### GENERAL VARS #################
CC             := javac
RUNCMD         := java
JARCMD         := jar -cvf
RM              = rm -f
MKDIR_P         = mkdir -p

ifdef OS # windows
	JAVAFX_PATH    = ./lib/javafx-sdk-11.0.2-windows/lib
else
	JAVAFX_PATH    = ./lib/javafx-sdk-11.0.2-unix/lib
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

TESTFILE       := ./HelloWorld.java # TODO remove
CLASSFILES      = ./HelloWorld.class

TARGET         := ./bin/trainsim # TODO change this
DTARGET        := ./bin/trainsimdebug
TTARGET        := HelloWorld
JARTARGET      := ./bin/app.jar # TODO figure out how to use jar files
.PHONY         := clean runtest TrackController TrainController TrackModel TrainModel CTC # TODO add tar for packaging


# Rules
all: $(TARGET)
debug: $(DTARGET)
runtest: $(TTARGET)
	$(RUNCMD) $(JFLAGS) $(TTARGET)
jar: $(TTARGET)
	$(JARCMD) $(JARTARGET) *.class

#regular target
$(TARGET): $(JAVAFILES)
	$(CC) $(JFLAGS) $(JAVAFILES)

#debug target
$(DTARGET): $(JAVAFILES)
	$(CC) $(JFLAGS) $(DFLAGS) $(JAVAFILES)


# Module Targets
# TODO figure out how multiple makefiles can make this easier


#javafx test target
$(TTARGET): $(TESTFILE)
	$(CC) $(JFLAGS) $(TESTFILE)

clean:
	$(RM) '*.class' *.class
