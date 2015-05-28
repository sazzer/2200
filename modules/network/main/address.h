#ifndef _2200_NETWORK_NETWORK_ADDRESS_H
#define _2200_NETWORK_NETWORK_ADDRESS_H

#include <string>

namespace Network {
  /**
   * Representation of a Network Address
   */
  class Address {
  public:
    /**
     * Construct the address with the given port. This will construct the address
     * using the catchall hostname instead of a specific one
     * @param port the port
     */
    Address(const short port) : Address("", port) {}
    /**
     * Construct the address with the given hostname and port
     * @param hostname The hostname
     * @param port The port
     */
    Address(const std::string& hostname, const short port) :
      hostname_(hostname),
      port_(port) {}

    /**
     * Get the hostname
     * @return the hostname
     */
    const std::string& hostname() const {
      return hostname_;
    }

    /**
     * Determine if we represent the Global address or not
     * @return True if we represent the global addrss. False if not
     */
    bool isGlobal() const {
      return hostname_ == "";
    }

    /**
     * Get the port number
     * @return the port number
     */
    short port() const {
      return port_;
    }
  private:
    /** The hostname. Empty string means the global address */
    const std::string hostname_;
    /** The port */
    const short port_;
  };
}

#endif // _2200_NETWORK_NETWORK_ADDRESS_H
