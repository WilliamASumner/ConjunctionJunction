# Makefile for team Conjunction Junction

# Vars
CC             := javac
RUNCMD         := java

MKDIR_P        = mkdir -p
JAVAFX_PATH    = ./javafx-sdk-11.0.2/lib
JAVAFX_MODULES = javafx.controls # NOTE: must be comma separated

JFLAGS         := --module-path=$(JAVAFX_PATH) --add-modules $(JAVAFX_MODULES)

MODULES        = TkC TkM TrC TrM CTC
JAVAFILES      := $(addsuffix .java,$(MODULES))
CLASSFILES     := $(addsuffix .class,$(MODULES))
TESTFILE       := HelloWorld.java # TODO remove
TESTFILECLASS  := HelloWorld.class

TARGET         := trainsim
DTARGET        := trainsimdebug
TTARGET        := HelloWorld
.PHONY         := clean runtest # TODO add tar for packaging

ifdef OS # running on windows
	RM = del
	MKDIR_P = mkdir
	JFXFLAGS = $(subst /,\,JFXFLAGS) # TODO test on windows
else # unix-like
	RM = rm -f
	MKDIR_P = mkdir -p
endif

# Rules

all: $(TARGET)
debug: $(DTARGET)
runtest: $(TTARGET)
	$(RUNCMD) $(JFLAGS) $(TTARGET)

#TODO add tar

#regular target
$(TARGET): $(JAVAFILES)
	$(CC) $(JFLAGS) $(JAVAFILES)

#debug target
$(DTARGET): $(JAVAFILES)
	$(CC) $(JFLAGS) $(DFLAGS) $(JAVAFILES)

#javafx test target
$(TTARGET): $(TESTFILE)
	$(CC) $(JFLAGS) $(TESTFILE)

clean:
	$(RM) $(CLASSFILES) $(TESTFILECLASS)
