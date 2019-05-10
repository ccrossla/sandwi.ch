/**
 * 
 */

const input = document.getElementById("input-field");

const output = document.getElementById("chat-text");


const wsRoute = document.getElementById("ws-route");

const socket = new WebSocket(wsRoute.value.replace("http", "ws"));


// photos to randomly assign users

let userimgs = ["https://img1.cookinglight.timeinc.net/sites/default/files/styles/4_3_horizontal_-_1200x900/public/image/2017/05/main/egg-in-nest-blt-sandwiches-1707p38.jpg?itok=-2zHWRHS",

"https://www.luvmyrecipe.com/wp-content/uploads/Schezwan-Chutney-03.jpg", "https://cms.splendidtable.org/sites/default/files/styles/w2000/public/green-godess-tuna-salad-sandwich_Alex-Lau_Bon-Appetit-LEDE_0.jpg?itok=q_s2Yyth",

"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQHxO0Yzkvlxi5MRoTkuOwhJ8i9uPEfbcVKeOS-_05Jki9nRUt5",

"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQeonSAgfy6uCiJ6GcFMHn6NknW9MmYSn3LyyqS3mo4uIh2kaNLKQ",

"https://milios.com/wp-content/uploads/2018/03/MS17_Sandwiches_5TheGodfather.jpg",

"https://img.newatlas.com/sandwich-warming-2.jpg?auto=format%2Ccompress&ch=Width%2CDPR&fit=crop&h=347&q=60&rect=134%2C244%2C1485%2C836&w=616&s=da39bbb0a416778a2b0470c9b484506e",

"https://www.pumpkinnspice.com/wp-content/uploads/2016/08/grilled-ham-cheese-sandwich-26-1024x683.jpg",

"https://www.cfacdn.com/img/order/COM/Menu_Refresh/Entree/Entree%20PDP/_0000s_0004_NEWStack6200001CFAPDPDeluxeSandwich1085png.png",

"https://www.organix.com/sites/organix.com/files/styles/1080x700/public/recipe/Main%20Pic%20_83.JPG?itok=JMc5rhwJ&timestamp=1508164665",

"https://boudinbakery.com/wp-content/uploads/2017/04/sandwich-tuna-full.jpeg"

];



// time

let now = new Date(Date.now());


let timeValue;


if (now.getHours() > 0 && now.getHours() <= 12) {

  timeValue= "" + now.getHours() + ":" + now.getMinutes() + ' AM';

} else if (now.getHours() > 12) {

  timeValue= "" + (now.getHours() - 12) + ":" + now.getMinutes() + ' PM';

} else if (now.getHours() == 0) {

  timeValue= "12 AM";

}

let curTime = timeValue;


// my messages

let msgPart1 = '<div class="d-flex justify-content-end mb-4"><div class="msg_cotainer_send">';

let msgPart2 = '<span class="msg_time_send">';

let msgPart3 =  '</span></div><div class="img_cont_msg"><img src="';

let msgPart4 = '" class="rounded-circle user_img_msg"></div></div>';


// received messages

let msgReceive1 = '<div class="d-flex justify-content-start mb-4"><div class="img_cont_msg"><img src=" ';

let msgReceive2 = ' " class="rounded-circle user_img_msg"></div><div class="msg_cotainer">';

let msgReceive3 = '<span class="msg_time">'

let msgReceive4 = '</span></div></div>';


// user entering chat

let newUserPart1 = '<li class="active"><div class="d-flex bd-highlight"><div class="img_cont"><img src=';

let newUserPart2 = ' class="rounded-circle user_img"><span class="online_icon"></span></div><div class="user_info"><span>';

let newUserPart3 = '</span><p>Joined at ' 

let newUserPart4 = ' </p></div></div></li>';


// temp user info

let loc = Math.floor(Math.random() * 10);

let myPic = userimgs[loc];

let myName = '';


// user object

function User(id, username, pic, type){

	this.id = id;

	this.username = username;

	this.pic = pic;

	this.type = type;

}

User.prototype.appear = function(){

	let str = newUserPart1 + this.pic + newUserPart2 + this.username + newUserPart3 + curTime;

	$(".contacts").append(str);

}


// current user

let me = new User(loc, myName, myPic, 'user');

let chatHistory = '';


// message object

function Message(content, time, pic, userID, username, type){

	this.content = content;

	this.time = time;

	this.pic = pic;

	this.userID = userID;

	this.username = username;

	this.type = type;


}

// messages i send

Message.prototype.write = function(){

	let msg = msgPart1 + this.content + msgPart2 + this.time + msgPart3 + this.pic + msgPart4;

	chatHistory = chatHistory.concat(msg);

	localStorage.setItem(me.id, chatHistory);

	console.log("wrote: " + chatHistory);

	$(".msg_card_body").append(msg);

	input.value = '';

}

// messages other people send

Message.prototype.receive = function(){

	let displayMsg = msgReceive1 + this.pic + msgReceive2 + this.content + msgReceive3 + this.time + msgReceive4;

	chatHistory = chatHistory.concat(displayMsg);

	localStorage.setItem(me.id, chatHistory);

	console.log("receive: " + chatHistory);

	$(".msg_card_body").append(displayMsg);

	input.value = '';

}


socket.addEventListener('open', function (event) {

	me.username = localStorage.getItem('uname');

	me.id = localStorage.getItem(me.username);

	

	// check if you have any saved chat history

	if(localStorage.getItem(me.id) != null){

		me.pic = localStorage.getItem(me.id+'pic');

		chatHistory = localStorage.getItem(me.id);

		$(".msg_card_body").append(localStorage.getItem(me.id));

		

	// you havent chatted yet

	}else{

		localStorage.setItem(me.id, chatHistory);

		localStorage.setItem(me.id+'pic', me.pic);

	}

	let myString = JSON.stringify(me);

	socket.send(myString);

});


input.onkeydown = (event) => {

	if(event.key ==='Enter') {

		let myMsg = new Message(input.value, curTime, myPic, loc, myName, 'msg');	

		let msgString = JSON.stringify(myMsg);	

		socket.send(msgString);

	}	

}

$( ".input-group-append" ).click(function() {

	
		console.log("hi");
  		let myMsg = new Message(input.value, curTime, myPic, loc, myName, 'msg');	

		let msgString = JSON.stringify(myMsg);	

		socket.send(msgString);

});


socket.addEventListener('message', function (event) {

	if(event.data === "close down"){

		localStorage.setItem(me.id, chatHistory);

		console.log("closing: " + chatHistory);

	}

	else{

		let obj = JSON.parse(event.data);

	if(obj.type === 'user'){

		let newUser = new User(obj.id, obj.username, obj.pic, obj.type);

		newUser.appear();

	}

	else{

		let newMsg = new Message(obj.content, obj.time, obj.pic, obj.userID, obj.username, obj.type);

		if(newMsg.userID === loc){

			newMsg.write();

		}

		else{

			newMsg.receive();

		}

	}

	}

	

});

	