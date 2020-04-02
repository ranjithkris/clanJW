# What is ClashOfClansLibrary?
[`ClashOfClansLibrary`](https://ranjithmasthikatte.github.io/ClashOfClansLibrary/) 
is a Java library to access Clash of Clans game related data using the REST API 
provided by the Supercell (https://developer.clashofclans.com/).

# How to use ClashOfClansLibrary?
First, download the jar file from the 
[`Releases`](https://github.com/ranjithmasthikatte/ClashOfClansLibrary/releases)
and add the ClashOfClansLibrary jar file to the your project's Build Path.

To access a Clash of Clans server, JSON Web Token is required. To get the JSON Web Token
and to know about the Clash of Clans API see (https://developer.clashofclans.com/)

To access the information related to a player, create a 
`COCPlayers` object and use this object to access any 
information related to a single player. To know the methods and fields see 
[`ClashOfClansLibrary`](https://ranjithmasthikatte.github.io/ClashOfClansLibrary/)

# Example
```.java
COCPlayers player1 = new COCPlayers(JWTOKEN, PLAYER_TAG);

System.out.println(player1.getPlayerClanName());
System.out.println(player1.getPlayerClanLargeBadgeUrl());
System.out.println(player1.getPlayerHeroName(Heroes.ARCHER_QUEEN));
System.out.println(player1.getPlayerName());
```