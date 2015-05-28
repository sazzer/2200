#include "server.h"
#include <set>
#include <sys/types.h>
#include <sys/socket.h>
#include <netdb.h>
#include <arpa/inet.h>

namespace Network {
  struct Server::Impl {
    std::set<std::shared_ptr<sockaddr_in> > pendingServers;
  };
  
  Server::Server() : pImpl(new Server::Impl) {
  }

  Server::~Server() {
  }

  
  void Server::addListener(const short port) {
    std::shared_ptr<sockaddr_in> addr(new sockaddr_in);
    addr->sin_family = AF_INET;
    addr->sin_port = htons(port);
    addr->sin_addr.s_addr = INADDR_ANY;
    pImpl->pendingServers.insert(addr);
  }
  
  void Server::addListener(const std::string& address, const short port) {
    std::shared_ptr<sockaddr_in> addr(new sockaddr_in);
    addr->sin_family = AF_INET;
    addr->sin_port = htons(port);
    inet_pton(AF_INET, address.c_str(), &(addr->sin_addr));
    pImpl->pendingServers.insert(addr);
  }

}
