
CC = javac
MAKE_DIR = /home/georg/ConjunctionJunction
BIN_DIR = ../../bin
#BIN_DIR = $(MAKE_DIR)/bin

ifdef OS # windows
	JAVAFX_PATH    =../../lib/javafx-sdk-11.0.2-windows/lib
else
	JAVAFX_PATH    =../../lib/javafx-sdk-11.0.2-unix/lib
endif 

# NOTE: must be comma separated
ifndef JAVAFX_MODULES
	JAVAFX_MODULES  = javafx.controls
endif

ifndef JFLAGS
	JFLAGS         := --module-path=$(JAVAFX_PATH) --add-modules $(JAVAFX_MODULES)
endif


TARGET_FILE = $(addsuffix /MainUI.class,$(BIN_DIR))
MODULE_DIRS =../TkC/
#TkM/ TnM/ TnC/ CTC/
GEN_FILES =../*.java
MODULE_FILES= $(addsuffix *.java,$(MODULE_DIRS))

export JAVAFX_PATH # export to the shell for running custom commands
export JFLAGS

.PHONY = default clean

catch:
	@echo "error: this file should only be run from the toplevel makefile"
	@exit 1

run: $(BIN_DIR)/MainUI.class
	(cd $(BIN_DIR) && $(RUNCMD) $(JFLAGS) MainGUI)

default: $(BIN_DIR)/MainUI.class

$(BIN_DIR)/MainUI.class:
	$(CC) $(JFLAGS) -cp $(BIN_DIR):$(MODULE_DIRS) $^ $(MODULE_FILES) $(GEN_FILES)

clean:
	@$(RM) $(TARGET_FILE) *.class
