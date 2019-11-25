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
SEP             =;

ifdef OS # windows
	JAVAFX_PATH    = $(MAKE_DIR)/lib/javafx-sdk-11.0.2-windows/lib
	SEP            = ;
else
	JAVAFX_PATH    = $(MAKE_DIR)/lib/javafx-sdk-11.0.2-unix/lib
	SEP            = :
endif

################# FLAG VARS ##################
#NOTE: must be comma separated
JAVAFX_MODULES  = javafx.controls,javafx.fxml
JFLAGS         := --module-path=$(JAVAFX_PATH) --add-modules $(JAVAFX_MODULES)
ANTLRPATH=$(MAKE_DIR)/lib/antlr-4.7.2-complete.jar
LINT_FLAGS=-Xlint:unchecked -Xlint:deprecation

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

run: src.txt $(TARGET_CLASS)
	$(RUNCMD) $(JFLAGS) -cp "$(BIN_DIR)$(SEP)$(ANTLRPATH)" $(TARGET)
#	$(RUNCMD) $(JFLAGS) -cp "$(BIN_DIR)" $(TARGET)

# java build text
src.txt: ./src/
	find ./src -name "*.java" > src.txt

# Result is dependent on all files in src dir
$(TARGET_CLASS): $(shell find ./src -type f)
#	$(CC) $(JFLAGS) -d $(BIN_DIR) @src.txt
	$(CC) $(JFLAGS) $(LINT_FLAGS) -cp "$(BIN_DIR)$(SEP)$(ANTLRPATH)" -d $(BIN_DIR) @src.txt

runv: src.txt $(TARGET_CLASS) # make whole project
	$(RUNCMD) $(JFLAGS) -cp "$(BIN_DIR)$(SEP)$(ANTLRPATH)" $(VTARGET)

runtest: $(TTARGET)
	$(RUNCMD) $(JFLAGS) $(TTARGET)

testrun: $(TESTCLASSFILES)
	$(RUNCMD) $(JFLAGS) -cp "$(BIN_DIR)" $(TESTTARGET)

$(TESTCLASSFILES): $(TESTFILE)
	$(CC) $(JFLAGS) -d $(BIN_DIR) $(TESTFILE)



clean:
	$(RM) $(BIN_DIR)/*.class
	$(RM) *.class
	$(RM) src.txt
