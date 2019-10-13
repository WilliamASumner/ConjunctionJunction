# Makefile for team Conjunction Junction

# Vars
CC             := javac
RUNCMD         := java
JARCMD         := jar -cvf

MKDIR_P        = mkdir -p
ifdef OS # windows
	JAVAFX_PATH    = ./lib/javafx-sdk-11.0.2-windows/lib
else
	JAVAFX_PATH    = ./lib/javafx-sdk-11.0.2-unix/lib
endif 
JAVAFX_MODULES = javafx.controls # NOTE: must be comma separated

JFLAGS         := --module-path=$(JAVAFX_PATH) --add-modules $(JAVAFX_MODULES)

MODULES        = TkC TkM TrC TrM CTC
JAVAFILES      := $(addsuffix .java,$(MODULES))
CLASSFILES     = $(addsuffix .class,$(MODULES))
TESTFILE       := HelloWorld.java # TODO remove
CLASSFILES     = HelloWorld.class

TARGET         := ./bin/trainsim
DTARGET        := ./bin/trainsimdebug
TTARGET        := HelloWorld
JARTARGET      := ./bin/app.jar
.PHONY         := clean runtest # TODO add tar for packaging

RM = rm -f
MKDIR_P = mkdir -p

# Rules

all: $(TARGET)
debug: $(DTARGET)
runtest: $(TTARGET)
	$(RUNCMD) $(JFLAGS) $(TTARGET)
jar: $(TTARGET)
	$(JARCMD) $(JARTARGET) *.class

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
	$(RM) '*.class' *.class
