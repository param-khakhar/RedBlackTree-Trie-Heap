logtrieerror= (echo 'ERROR '; echo 'EXPECTED_OUTPUT:'; echo "--------------"; cat Trie/EXPECTED_OUTPUT; echo "==============" ;echo "Your OUTPUT"; echo "--------------"; cat Trie/tmp;)
logrberror = (echo "ERROR"; echo "EXPECTED_OUTPUT:"; echo "--------------"; cat RedBlack/EXPECTED_OUTPUT; echo "==============" ;echo "Your OUTPUT:"; echo "--------------"; cat RedBlack/tmp;)
logpqerror = (echo 'ERROR '; echo 'EXPECTED_OUTPUT:'; echo "--------------"; cat PriorityQueue/EXPECTED_OUTPUT; echo "=============="; echo "Your OUTPUT:"; echo "--------------"; cat PriorityQueue/tmp; )
logpmerror = (echo 'ERROR '; echo 'EXPECTED_OUTPUT:'; echo "--------------"; cat ProjectManagement/EXPECTED_OUTPUT; echo "=============="; echo "Your OUTPUT:"; echo "--------------"; cat ProjectManagement/tmp; )

all: triebuild triecheck rbtreebuild rbtreecheck pqbuild  pqcheck pmbuild pmcheck clean
testcase: pqtestcase rbtestcase trietestcase pmtestcase
build: triebuild rbtreebuild pqbuild pmbuild
check: triecheck pqcheck rbtreecheck pmcheck
trie: triebuild triecheck trieclean
rbtree: rbtreebuild rbtreecheck rbtreeclean
pq: pqbuild pqcheck pqclean
pm: pmbuild pmcheck pmclean

triebuild:
	@echo "**********"
	@echo "Building Trie"
	@echo "**********"
	@javac Trie/*.java
triecheck:
	@echo "**********"
	@echo "Checking Trie"
	@echo "**********"
	@java Trie.TrieDriverCode >Trie/tmp
	@cmp --silent Trie/tmp Trie/EXPECTED_OUTPUT && echo 'SUCCESS' || $(call logtrieerror)
trieclean:
	-@rm Trie/tmp -f
	-@rm Trie/*.class -f

rbtreebuild:
	@echo "\n**********"
	@echo "Building Red-Black Tree"
	@echo "**********"
	@javac RedBlack/*.java
rbtreecheck:
	@echo "\n**********"
	@echo "Checking Red-Black"
	@echo "**********"
	@java RedBlack.RedBlackDriverCode >RedBlack/tmp
	@cmp --silent RedBlack/tmp RedBlack/EXPECTED_OUTPUT && echo 'SUCCESS' || $(call logrberror)
rbtreeclean:
	-@rm RedBlack/tmp -f 
	-@rm RedBlack/*.class -f

pqbuild:
	@echo "\n**********"
	@echo "Building PriorityQueue"
	@echo "**********"
	@javac PriorityQueue/*.java
pqcheck:
	@echo "\n**********"
	@echo "Checking PriorityQueue"
	@echo "**********"
	@java PriorityQueue.PriorityQueueDriverCode >PriorityQueue/tmp
	@cmp --silent PriorityQueue/tmp PriorityQueue/EXPECTED_OUTPUT && echo 'SUCCESS' || $(call logpqerror)
pqclean:
	-@rm PriorityQueue/tmp -f
	-@rm PriorityQueue/*.class -f

pmbuild:
	@echo "\n**********"
	@echo "Building ProjectManagement"
	@echo "**********"
	@javac ProjectManagement/*.java
pmcheck:
	@echo "\n**********"
	@echo "Checking ProjectManagement"
	@echo "**********"
	@java ProjectManagement.Scheduler_Driver >ProjectManagement/tmp
	@cmp --silent ProjectManagement/tmp ProjectManagement/EXPECTED_OUTPUT && echo 'SUCCESS' || $(call logpmerror)
pmclean:
	-@rm ProjectManagement/tmp -f
	-@rm ProjectManagement/*.class -f


clean: trieclean pqclean rbtreeclean pmclean
	-@rm -f Util/*.class
	@echo "Done"

trietestcase:
	@make triebuild
	@java Trie.TrieDriverCode >Trie/EXPECTED_OUTPUT
	@make clean
pqtestcase:
	@make pqbuild
	@java PriorityQueue.PriorityQueueDriverCode >PriorityQueue/EXPECTED_OUTPUT
	@make clean
rbtestcase:
	@make rbtreebuild
	@java RedBlack.RedBlackDriverCode >RedBlack/EXPECTED_OUTPUT
	@make clean
pmtestcase:
	@make pmbuild
	@java ProjectManagement.Scheduler_Driver >ProjectManagement/EXPECTED_OUTPUT
	@make clean