const url = 'http://localhost:8080';
let stompClient;
let selectedUser;


function connectToChat(userName) {
    console.log("connecting to chat.......");
    // constroller 에 있는 mapping 주소를 넣어줌
    let socket = new SockJS(url + '/chat' + userName);
    // socket 과 stomp에 대한 참조는 index.html 의 상위에 서브 참조로 올라가있음
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame){
        console.log("connected to: " + frame);
        // message controller 에 있는 주소
        stompClient.subscribe("/topic/messages/" + userName, function (response) {
            // 들어온 응답을 json으로 형변환 한다
            let data = JSON.parse(response.body);
            console.log(data);
        });
    });
}


function sendMsg(from, text) {
    // message controller
    // websocket configuration
    stompClient.send("/app/chat/" + selectedUser, {}, JSON.stringify({
        // messageModel
        fromLogin: from,
        message: text
    }));
}

function registration() {
    let userName = document.getElementById("userName").value;
    // usercontroller
    $.get(url + "/registeration/" + userName, function(response){
        
        connectToChat(userName);

    }).fail(function (error) {
        
        if(error.status === 400) {
            alert("Login is already busy!");
        }

    });
}

function fetchAll() {

    // userscontroller
    $.get(url + "/fetchUsers", function(response){
        
       let users = response;
       let usersTemplateHTML = "";
       for (let i = 0; i < users.length; i++){
        usersTemplateHTML = usersTemplateHTML + `<img alt="avatar" height="55px"
        src="https://rtfm.co.ua/wp-content/plugins/all-in-one-seo-pack/images/default-user-image.png"
                    width="55px"/>
            <div class="about">
                <div class="name">${users[i]}</div>
                <div class="status">
                    <i class="fa fa-circle offline"></i>
                </div>
            </div>`;
       }
    
       $('#usersList').html(usersTemplateHTML);

    });
}