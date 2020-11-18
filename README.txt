COMP310 FALL 2020 Homework08

Jolisa Brown- jmb26
Wilson Jeng - wmj1 

ChatApp 
-----------------------------------------------------------------------------------------------------------

To start ChatApp, run the LaunchClient configuration which is located under the 
HW08 homework folder.  
  
For our ChatApp client implementation, we decided to forgo the “username” 
and “server name” inputs  and instead pre-bind those visual identifier values for 
our exported stubs.  As such, to get started with connecting to another person, just go
 directly into the provided discovery server interface of the client GUI and enter a 
category to begin using the discovery server to find an endpoint 
to connect to.  Additionally, we have left a manual connection method for use 
with connecting to those that are unable to join the discovery server.  Both
ways of connecting will obtain a stub of the person you are connecting to 
and populate the dropdown list of connected hosts with the obtained stub. (Please note 
that after connecting with a user, you must next manually click and select that user's name/instance 
in the "Connected Hosts" dropdown in order for the "Connected Host's Chatrooms" dropdown 
to populate with the user's rooms. Auto-connect is supported in that any person that connects 
with you will automatically be connected to you as well and receive your stub as well.  

Once connected to other people, you can view the list of the chatrooms they are
connected to in the “Connected Host’s Chatrooms” dropdown menu.  Here, you
are able to select any chat room and click the “join” button to join that chatroom 
where a well defined join message is also sent to all members of 
the chatroom indicating for the people to add your stub to theirs.  Additionally, 
you can make chatrooms with the “make it!” button and these chat rooms will
show in the “your connected chatrooms” dropdown menu.  Selecting these chatrooms
allows you to invite any connected host to them and will add your chat room to their chat rooms.  

By joining or creating a chatroom, a chatroom GUI will appear as a tabbed pane in the client
GUI.  Within the chatroom GUI, you can send a text message to all other members of the 
chat room through the send text button which will send text that is input in the input box
above it  and you can send a poem through the send poem button which will 
send a poem as a component.  This is our unknown command implementation.  

Pressing the leave room button will leave the chatroom and send a leave
Command, which is a well known message type, to the rest of the members of the room.  
The Exit All and Quit button will then close the chat app client.  

