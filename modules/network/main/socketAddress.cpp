#include "socketAddress.h"
#include <cstring>

namespace Network {
  void populateSockaddr(const Address& address, sockaddr_in& sockaddr) {
    memset(&sockaddr, 0, sizeof(sockaddr_in));
    sockaddr.sin_family = AF_INET;
    sockaddr.sin_port = htons(address.port());
    if (address.isGlobal()) {
      sockaddr.sin_addr.s_addr = INADDR_ANY;
    } else {
      inet_pton(AF_INET, address.hostname().c_str(), &(sockaddr.sin_addr));
    }
  }
}
