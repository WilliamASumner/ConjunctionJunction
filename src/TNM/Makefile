# Makefile for TrainModel Module

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
CLASS_PATH      := ../../bin/TnM

######### TRACK CONTROLLER #############
TNM_MODULE     = TrainModel TrainModelGUI
TNM_SRCDIR     = ../../src/TnM/
TNM_BINDIR     = ../../bin/TnM/

TNM_JAVAFILES  = $(addsuffix .java,$(addprefix $(TNM_SRCDIR),$(TNM_MODULE)))
TNM_CLASSFILES = $(addsuffix .class,$(addprefix $(TNM_BINDIR),$(TNM_MODULE)))

#JAVAFILES      := $(addsuffix .java,$(MODULES))
#CLASSFILES     = $(addsuffix .class,$(MODULES))

################# TEST VARS ##################

TTARGET_CLASS  := $(addprefix $(TNM_BINDIR),TrainModelGUI.class)
TTARGET        := TrainModelGUI
.PHONY         := clean runtest

# Rules
default: $(TTARGET_CLASS)
	$(RUNCMD) -cp $(CLASS_PATH) $(JFLAGS) $(TTARGET)

$(TTARGET_CLASS): $(TNM_JAVAFILES) $(BLOCK_JAVAFILES)
	$(CC) $(JFLAGS) -d $(TNM_BINDIR) $(BLOCK_JAVAFILES) $(TNM_JAVAFILES)

clean:
	$(RM) '*.class' *.class
