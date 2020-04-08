# What is clanJW?
[`clanJW`](https://ranjithmasthikatte.github.io/clanJW/) 
is a Java Wrapper to access Clash of Clans game related data using the REST API 
provided by the Supercell (https://developer.clashofclans.com/).

# How to use clanJW?
First, download the jar file from the 
[`Releases`](https://github.com/ranjithmasthikatte/clanJW/releases)
and add the clanJW jar file to the your project's Build Path. 

To access a Clash of Clans server, JSON Web Token is required. To get the JSON Web Token
and to know about the Clash of Clans API see (https://developer.clashofclans.com/)

To access the information related to a player, create a 
`COCPlayers` object and use this object to access any 
information related to a single player. To know the methods and fields see 
[`clanJW`](https://ranjithmasthikatte.github.io/clanJW/)

# Example
```.java
private static final String JWTOKEN = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiIsImtpZCI6IjI4YTMxOGY3LTAwMDAtodk6ut03ZmExLTJjNzQzM2M2Y2NhNSJ9.eyJpc3MiOiJzdXBlcmNlbGwiLCJhdWQiOiJzdXBlcmNlbGw6Z2FtZWFwaSIsImp0aSI6IjliZDMwZmUyLTFiYTQtNGU2MC05MGQzLTMzYjg1OWRlOTI1NCIsImlhdCI6MTU4NTc3ODQ1NSwiufjgIjoiZGV2ZWxvcGVyLzc5Njc2MDRiLTJiNGEtNzE2Yy04Mzg5LTZkM2M3ZGU5MGQxZCIsInNjb3BlcyI6WyJjbGFzaCJdLCJsaW1pdHMiOlt7InRpZXIiOiJkZXZlbG9wZXIvc2lsdmVyIiwidHlwZSI6InRocm90dGxpngjfhSx7ImNpZHJzIjpbIjE5NC45NS4yLjI0NCJdLCJ0eXBlIjoiY2xpZW50In1dfQ.rK5nmh6e7uiv3UxsSnnxvEt-vixfg9JsHTrsJpAWjt2pUo4kULF5Oervh-nWlNDrEmEE_loZYBQf_vfQwmu-VA";
private static final String PLAYER_TAG = "#Y989RF7Z";

COCPlayers player1 = new COCPlayers(JWTOKEN, PLAYER_TAG);

System.out.println(player1.getPlayerClanName());
System.out.println(player1.getPlayerClanLargeBadgeUrl());
System.out.println(player1.getPlayerHeroName(Heroes.ARCHER_QUEEN));
System.out.println(player1.getPlayerName());
```