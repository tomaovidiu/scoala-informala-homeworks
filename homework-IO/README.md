# ElectingAplication
Implemented a mayor - electing application.

In this application you will define the list of mayor candidates (only their names).
Every time a citizen votes, they will need to enter their personal details(CNP, Name) and candidate they are voting for.
Every vote is saved as a new line in a file (votes.txt) in the following format: CNP, Name of voter, Name of candidate.
Once the voting is done, the vote counting process is started.
Every line from the file is read and the following rules are applied to the votes:
 - If someone tries to vote twice (CNP shows up more than once) they receive a fine and all of their votes are invalidated.
 - If someone fails to choose a candidate, their vote is invalidated.
 - All valid votes are counted. Candidate with highest number of votes is elected as mayor.
