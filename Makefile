# Makefile for team Conjunction Junction

############### GENERAL VARS #################
CC             := javac
RUNCMD         := java
JARCMD         := jar -cvf
RM              = rm -f
MKDIR_P         = mkdir -p

MAKE_DIR        = $(PWD)
MAKE_DIR        = .
BIN_DIR         = $(addsuffix /bin,$(MAKE_DIR))
BIN_DIR         = ./bin

ifdef OS # windows
	JAVAFX_PATH    = $(MAKE_DIR)/lib/javafx-sdk-11.0.2-windows/lib
else
	JAVAFX_PATH    = $(MAKE_DIR)/lib/javafx-sdk-11.0.2-unix/lib
endif

#NOTE: must be comma separated
JAVAFX_MODULES  = javafx.controls,javafx.fxml
JFLAGS         := --module-path=$(JAVAFX_PATH) --add-modules $(JAVAFX_MODULES)
ANTLRPATH=$(MAKE_DIR)/lib/antlr-4.7.2-complete.jar

############### MODULE VARS ##################
MODULES         = TkC TkM TrC TrM CTC

######### TRACK CONTROLLER #############
TKC_MODULEFILES   = ./src/TkC/*.class
MAIN_MODULEFILES   = ./src/MainUI/*.class

#JAVAFILES      := $(addsuffix .java,$(MODULES))
#CLASSFILES     = $(addsuffix .class,$(MODULES))

################# TEST VARS ##################

# TODO REMOVE
TESTFILE       := ./HelloWorld.java
TESTCLASSFILES      = $(MAKE_DIR)/HelloWorld.class
TESTTARGET     := HelloWorld

################# TARGET VARS ################

TARGET         := MainUI
TARGET_CLASS   := ./bin/MainUI.class
TTARGET        := HelloWorld
.PHONY         := clean runtest


# for all:
# make shared class files
# then make each dirs files

default: src.txt $(TARGET_CLASS)

testrun: $(TESTCLASSFILES)
	$(RUNCMD) $(JFLAGS) -cp "$(BIN_DIR)" $(TESTTARGET)

$(TESTCLASSFILES): $(TESTFILE)
	$(CC) $(JFLAGS) -d $(BIN_DIR) $(TESTFILE)

run: src.txt $(TARGET_CLASS)
#	$(RUNCMD) $(JFLAGS) -cp "$(BIN_DIR);$(ANTLRPATH)" $(TARGET)
	$(RUNCMD) $(JFLAGS) -cp "$(BIN_DIR)" $(TARGET)

# java build text
src.txt: ./src/
	find ./src -name "*.java" > src.txt


#$(TARGET_CLASS):
#	@$(MAKE) -C ./src/
#	@$(MAKE) -C ./src/TkC
#	#@$(MAKE) -C ./src/TkM
#	#@$(MAKE) -C ./src/CTC
#	#@$(MAKE) -C ./src/TnC
#	#@$(MAKE) -C ./src/TnM
#	@$(MAKE) -C ./src/MainUI default

# Result is dependent on all files in src dir
$(TARGET_CLASS): $(shell find ./src -type f)
	$(CC) $(JFLAGS) -d $(BIN_DIR) @src.txt
#	$(CC) $(JFLAGS) -Xlint:unchecked -d $(BIN_DIR) @src.txt

runtest: $(TTARGET)
	$(RUNCMD) $(JFLAGS) $(TTARGET)

#javafx test target
$(TTARGET): $(TESTFILE)
	$(CC) $(JFLAGS) $(TESTFILE)

clean:
	$(RM) $(BIN_DIR)/*.class
	$(RM) *.class
	$(RM) src.txt
