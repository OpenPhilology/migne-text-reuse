This project sets a frame for application of variable text alignment algorithms to detect and analyse historical text reuse in the Patrologia as published by the French cleric Jaques Paul Migne.

A set of input-, output- and temporary folders can be specified in the XML configuration file, as well as pointers to the import, export and alignment algorithms that should be used in each run. *Source* and *target* refer to domain and range texts of the citations.

To add alignment algorithms, simply implement the Aligner trait/interface and point to the new implementation in the configuration file. For examples see RETASAligner and JavaRETASAligner.

The project has been supported by the digital humanities research consortium [**DARIAH-DE**](https://www.de.dariah.eu/) and the German Ministry for Education and Research [**BMBF**](https://www.bmbf.de/).
