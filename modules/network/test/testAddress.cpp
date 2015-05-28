#include "catch.hpp"
#include "address.h"
#include "socketAddress.h"

TEST_CASE("Specific Address", "[address]") {
  const Network::Address address("127.0.0.1", 12345);

  SECTION("Values") {
    CHECK(address.hostname() == "127.0.0.1");
    CHECK(address.port() == 12345);
    CHECK(address.isGlobal() == false);
  }
  
  SECTION("To Sockaddr") {
    sockaddr_in addr;
    populateSockaddr(address, addr);

    CHECK(addr.sin_family == AF_INET);
    CHECK(addr.sin_port == htons(12345));
    CHECK(addr.sin_addr.s_addr == 0x0100007f); // 01.00.00.7f == 127.0.0.1
  }
}

TEST_CASE("Global Address", "[address]") {
  const Network::Address address(12345);

  SECTION("Values") {
    CHECK(address.hostname() == "");
    CHECK(address.port() == 12345);
    CHECK(address.isGlobal() == true);
  }

  SECTION("To Sockaddr") {
    sockaddr_in addr;
    populateSockaddr(address, addr);

    CHECK(addr.sin_family == AF_INET);
    CHECK(addr.sin_port == htons(12345));
    CHECK(addr.sin_addr.s_addr == 0x00000000); // 00.00.00.00
  }
}

