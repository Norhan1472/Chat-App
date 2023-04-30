import { Component, OnInit } from '@angular/core';
import * as SockJs from "sockjs-client";
import * as Stomp from "stompjs";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  disabled = true;
  constructor(){}
  
  ngOnInit(): void {
    this.connect();
  }
  title = 'frontend';
  chats:string[]=[];
  newmessage!:string ;
  private stompClient!: Stomp.Client ;

  setConnected(connected: boolean) {
    this.disabled = !connected;

    if (connected) {
      this.chats = [];
    }
  }
  
  connect(){
    const socket = new SockJs("http://localhost:8080/chat");
    this.stompClient= Stomp.over(socket);
    const _this = this;
    this.stompClient.connect({}, function(frame){
      console.log('connected '+frame);
      _this.stompClient.subscribe('/start/initial', function(hello){
        //console.log("here");
        console.log(JSON.parse(hello.body));
        //show message
        _this.showMessage(JSON.parse(hello.body));
      });
    });
  }

  showMessage(message: string){
    this.chats.push(message);
    //console.log("Hello22");
    //console.log(this.chats);
  }

  sendMessage(){
    this.stompClient.send("/current/resume",{}, JSON.stringify(this.newmessage));
    this.newmessage="";
  }
}
