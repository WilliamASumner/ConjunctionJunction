# Makefile for team Conjunction Junction

############### COMMAND VARS #################
CC             := javac
RUNCMD         := java
JARCMD         := jar -cvf
RM              = rm -f
MKDIR_P         = mkdir -p

BIN_DIR         = $(addsuffix /bin,$(MAKE_DIR))
BIN_DIR         = $(MAKE_DIR)/bin
SEP             =;

ifdef OS # windows
	JAVAFX_PATH    = $(MAKE_DIR)/lib/javafx-sdk-11.0.2-windows/lib
	MAKE_DIR       = $(shell cygpath -ma .)
	SEP            = ;
	PACKAGE_NAME  := cjunction
else # unix/linux
	JAVAFX_PATH    = $(MAKE_DIR)/lib/javafx-sdk-11.0.2-unix/lib
	MAKE_DIR       = ${CURDIR}
	SEP            = :
	PACKAGE_NAME  := cjunction-unix
endif

################# PATH/FLAG VARS ##################
#NOTE: must be comma separated
JAVAFX_MODULES  = javafx.controls
JFLAGS         := --module-path=$(JAVAFX_PATH) --add-modules $(JAVAFX_MODULES)
ANTLR          := antlr-4.7.2-complete.jar
ANTLR_LIB      :=$(MAKE_DIR)/lib/$(ANTLR)
PARSING_PATH   :=$(MAKE_DIR)/src/TkC/parsing/
LINT_FLAGS=-Xlint:unchecked -Xlint:deprecation

################# TARGET VARS ################
### Package Name
PACKAGE       := cjunction

### ONE JAR
ONEJAR_ROOT    := $(MAKE_DIR)/root
ONEJAR_LIBS    := $(ONEJAR_ROOT)/lib/$(ANTLR)

### Target files
TARGET         := MainUI
TARGET_CLASS   := $(MAKE_DIR)/bin/$(PACKAGE)/$(TARGET).class
PACKAGE_DIR    := $(MAKE_DIR)/$(PACKAGE_NAME)/ConjunctionJunctionProject
DELIVERABLE    := $(PACKAGE_NAME).zip
TARGET_JAR     := $(ONEJAR_ROOT)/main/$(PACKAGE)-module.jar
FINAL_JAR      := $(PACKAGE_DIR)/$(PACKAGE).jar

### Phony
.PHONY         := clean runtest check_status

################# ZIP AND CP Commands      ################
ifdef OS
	ZIPCMD     := cd $(PACKAGE_DIR)/.. && zip -9 -r -y -q $(DELIVERABLE) . && mv $(DELIVERABLE) ../
	DLLCP       = cp -r $(JAVAFX_PATH)/../bin/* $(PACKAGE_DIR)
else
	ZIPCMD     := tar -czf $(DELIVERABLE) -C $(PACKAGE_DIR)/.. .
endif


################# Build Directives  ################

default: check_status src.txt $(TARGET_CLASS)

check_status:
	./check.sh

run: src.txt $(TARGET_CLASS)
	$(RUNCMD) $(JFLAGS) -cp "$(BIN_DIR)$(SEP)$(ANTLR_LIB)" $(PACKAGE).$(TARGET)

# Generate src list of all available java files
# Changes on any change to files lower directories, or if missing specific files
src.txt: $(shell find ./src -type f) $(MAKE_DIR)/src/TkC/parsing/TkcParser.java
	find ./src -name "*.java" > src.txt

# Rebuild if src list has changed
$(TARGET_CLASS): src.txt
	$(CC) $(JFLAGS) $(LINT_FLAGS) -cp "$(BIN_DIR)$(SEP)$(ANTLR_LIB)" -d $(BIN_DIR) @src.txt

# Generate antlr java files if they don't already exist
$(MAKE_DIR)/src/TkC/parsing/TkcParser.java: $(MAKE_DIR)/src/TkC/parsing/Tkc.g4 $(MAKE_DIR)/src/TkC/parsing/TkcLeft.g4
	java -Xmx500M -cp ".$(SEP)$(ANTLR_LIB)$(SEP)$(PARSING_PATH)" org.antlr.v4.Tool $(PARSING_PATH)/Tkc.g4 -o $(PARSING_PATH)
	java -Xmx500M -cp ".$(SEP)$(ANTLR_LIB)$(SEP)$(PARSING_PATH)" org.antlr.v4.Tool $(PARSING_PATH)/TkcLeft.g4 -o $(PARSING_PATH)
	cd $(PARSING_PATH) && ./add_package.sh $(PACKAGE) # add package name

# Generate final deliverable - $(PACKAGE).zip
package: $(DELIVERABLE)

# Generate zipped deliverable, depends on final jar
$(DELIVERABLE): $(PACKAGE_DIR) $(FINAL_JAR)
	$(ZIPCMD)

# Generate final app jar, depends on general target jar and package dir (for output)
$(FINAL_JAR): $(TARGET_JAR) $(ONEJAR_LIBS)
	cp $(MAKE_DIR)/lib/one-jar-appgen-0.97.jar $(ONEJAR_ROOT)
	cp $(MAKE_DIR)/manifests/boot-manifest.mf $(ONEJAR_ROOT)
	cd $(ONEJAR_ROOT) && jar xf one-jar-appgen-0.97.jar
	cd $(ONEJAR_ROOT) && jar -cvfm $(FINAL_JAR) $(MAKE_DIR)/manifests/boot-manifest.mf .



# Generate dir for zipping
$(PACKAGE_DIR):
	$(MKDIR_P) $(PACKAGE_DIR)
	$(MKDIR_P) $(PACKAGE_DIR)/imgs
	$(MKDIR_P) $(PACKAGE_DIR)/rsrc
	cp $(MAKE_DIR)/imgs/* $(PACKAGE_DIR)/imgs
	cp $(MAKE_DIR)/rsrc/* $(PACKAGE_DIR)/rsrc

# Generate jar of only ConjunctionJunction code
$(TARGET_JAR): $(TARGET_CLASS) $(ONEJAR_ROOT)
	cd $(MAKE_DIR)/bin && jar --manifest=../manifests/MANIFEST.MF --create --file $(TARGET_JAR) cjunction/

# Generate onejar root
$(ONEJAR_ROOT):
	$(MKDIR_P) $(ONEJAR_ROOT)/main
	$(MKDIR_P) $(ONEJAR_ROOT)/lib

# Copy required libraries to the OneJar directory
$(ONEJAR_LIBS): $(ONEJAR_ROOT)
	cp -r $(JAVAFX_PATH)/ $(ONEJAR_ROOT)/lib/
	cp $(ANTLR_LIB) $(ONEJAR_ROOT)/lib/
	$(DLLCP)

# Run partial command
# run with runv VTARGET=class-name
runclass: src.txt $(TARGET_CLASS) # make single part of project
	$(RUNCMD) $(JFLAGS) -cp "$(BIN_DIR)$(SEP)$(ANTLR_LIB)" $(VTARGET)

clean:
	$(RM) $(BIN_DIR)/*.class
	$(RM) *.class
	$(RM) $(MAKE_DIR)/src.txt
	$(RM) $(MAKE_DIR)/src/TkC/parsing/Tkc*.{java,interp,tokens,class}
	$(RM) -r $(ONEJAR_ROOT)
	$(RM) -r $(BIN_DIR)/$(PACKAGE)
	$(RM) -r $(PACKAGE_DIR)
	$(RM) $(MAKE_DIR)/$(DELIVERABLE)
	$(RM) -r $(PACKAGE)
