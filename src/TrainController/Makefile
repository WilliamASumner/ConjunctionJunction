# Makefile for TrainController Module

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
CLASS_PATH      := ../../bin/TNC

######### TRAIN CONTROLLER #############
TNC_MODULE     = TrainController TrainControllerGUI Power PowerGUI TrainControllerMain TrainControllerMainGUI
TNC_SRCDIR     = ../../src/TrainController/
TNC_BINDIR     = ../../bin/

TNC_JAVAFILES  = $(addsuffix .java,$(addprefix $(TNC_SRCDIR),$(TNC_MODULE)))
TNC_CLASSFILES = $(addsuffix .class,$(addprefix $(TNC_BINDIR),$(TNC_MODULE)))

#JAVAFILES      := $(addsuffix .java,$(MODULES))
#CLASSFILES     = $(addsuffix .class,$(MODULES))

# ################# TEST VARS ##################

# TTARGET_CLASS  := $(addprefix $(TNC_BINDIR),TrainControllerGUI.class)
# TTARGET        := TrainControllerGUI
# .PHONY         := clean runtest

# # Rules
# default: $(TTARGET_CLASS)

# run: $(TTARGET_CLASS)
# 	$(RUNCMD) $(JFLAGS) -cp $(TNC_BINDIR) $(TTARGET)


# $(TTARGET_CLASS): $(TNC_JAVAFILES) $(BLOCK_JAVAFILES)
# 	$(CC) $(JFLAGS) -d $(TNC_BINDIR) $(BLOCK_JAVAFILES) $(TNC_JAVAFILES)

# clean:
# 	$(RM) '*.class' *.class $(TNC_CLASSFILES)
