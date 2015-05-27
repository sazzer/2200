#include "server.h"
#include <uv.h>
#include <iostream>

namespace Server {
  static uv_loop_t* loop;
  
  void onNewConnection(uv_stream_t* server, int status) {
    std::cout << "Connected" << std::endl;
    if (status < 0) {
      return;
    }
    
    uv_tcp_t* client = new uv_tcp_t;
    uv_tcp_init(loop, client);
    uv_accept(server, (uv_stream_t*)client);
    uv_close((uv_handle_t*)client, NULL);
  }
  
  void run() {
    loop = uv_default_loop();
    
    uv_tcp_t server;
    uv_tcp_init(loop, &server);
    
    sockaddr_in addr;
    uv_ip4_addr("0.0.0.0", 12345, &addr);
    
    uv_tcp_bind(&server, (const sockaddr*)&addr, 0);
    int r = uv_listen((uv_stream_t*)&server, 5, onNewConnection);
    if (r) {
      throw "Oops";
    }
    uv_run(loop, UV_RUN_DEFAULT);
  }
}
