ifndef BIN_DIR
	BIN_DIR     = ../bin/
else
	BIN_DIR     = $(MAKE_DIR)/bin/
endif

CC = javac

ifdef OS # windows
	JAVAFX_PATH    = ../lib/javafx-sdk-11.0.2-windows/lib
else
	JAVAFX_PATH    = ../lib/javafx-sdk-11.0.2-unix/lib
endif 

# NOTE: must be comma separated
ifndef JAVAFX_MODULES
	JAVAFX_MODULES  = javafx.controls
endif

ifndef JFLAGS
	JFLAGS         := --module-path=$(JAVAFX_PATH) --add-modules $(JAVAFX_MODULES)
endif


.PHONY: clean default

SRCS       = $(wildcard *.java)
OBJS       = $(patsubst %.java, %.class, $(SRCS))
OBJ_FILES = $(addprefix $(BIN_DIR),$(OBJS))

default:
	$(CC) $(JFLAGS) -d $(BIN_DIR) $(SRCS)

clean:
	$(RM) $(OBJ_FILES) *.class
