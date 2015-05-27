#include "server.h"
#include <uv.h>
#include <iostream>

namespace Server {
    void onNewConnection(uv_stream_t* server, int status) {
        std::cout << "Connected" << std::endl;
    }

    void run() {
        auto loop = uv_default_loop();

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
