#ifndef _2200_NETWORK_NETWORK_SERVER_H
#define _2200_NETWORK_NETWORK_SERVER_H

#include <memory>
#include <string>

namespace Network {
  /**
   * Class representing a network server
   */
  class Server {
  public:
    /**
     * Default constructor
     */
    Server();
    /**
     * Shutdown the server
     */
    ~Server();
    /** Don't allow the Server to be copied */
    Server(const Server&) = delete;
    /** Don't allow the Server to be assigned */
    Server& operator=(const Server&) = delete;

    /**
     * Add a new listener to the server. This listens on all interfaces.
     * @param port The port to listen on
     */
    void addListener(const short port);

    /**
     * Add a new listener to the server. This listens on a specific address.
     * @param address The address to listen on
     * @param port The port to listen on
     */
    void addListener(const std::string& address, const short port);

    /**
     * Start running the server. This method will not return until the server stops
     */
    void run();
  protected:
  private:
    /** The internal implementation structure */
    struct Impl;
    /** The internal implementation */
    std::shared_ptr<Impl> pImpl;
  };
}

#endif // _2200_NETWORK_NETWORK_SERVER_H
