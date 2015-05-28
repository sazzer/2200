#include "server.h"
#include <list>
#include <sys/types.h>
#include <sys/socket.h>
#include <netdb.h>
#include <arpa/inet.h>

namespace Network {
  struct Server::Impl {
    std::list<Address> pendingServers;
  };

  Server::Server() : pImpl(new Server::Impl) {
  }

  Server::~Server() {
  }


  void Server::addListener(const Address address) {
    pImpl->pendingServers.push_back(address);
  }

  void Server::run() {

  }
}
