#ifndef _2200_NETWORK_NETWORK_SOCKETADDRESS_H
#define _2200_NETWORK_NETWORK_SOCKETADDRESS_H

#include <arpa/inet.h>
#include "address.h"

namespace Network {
  /**
   * Populate the given Sockaddr structure from the given address
   * @param address The address to get the details from
   * @param sockaddr The sockaddr_in structure to populate
   */
  void populateSockaddr(const Address& address, sockaddr_in& sockaddr);
}

#endif // _2200_NETWORK_NETWORK_SOCKETADDRESS_H
