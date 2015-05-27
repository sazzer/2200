#include "catch.hpp"
#include "users/password.h"
#include <regex>
/** Regular Expression to match the Salt, which is a UUID */
static const auto SALT_REGEX = std::regex("^[a-z0-9]{8}-[a-z0-9]{4}-[a-z0-9]{4}-[a-z0-9]{4}-[a-z0-9]{12}$");

/** Regular Expression to match the Hash, which is an SHA256 hash as hex pairs  */
static const auto HASH_REGEX = std::regex("^[a-z0-9]{64}$");

TEST_CASE("Hashing a password", "[password]") {
  const Users::Password password = Users::Password::hash("password");

  CHECK(std::regex_match(password.salt(), SALT_REGEX));
  CHECK(std::regex_match(password.hash(), HASH_REGEX));
}

TEST_CASE("Comparing Passwords", "[password]") {
  const Users::Password password = Users::Password::hash("password", "salt");
  
  SECTION("Comparing to another hashed password") {
    CHECK(Users::Password::hash("password", "salt") == password);
    CHECK_FALSE(Users::Password::hash("password", "different") == password);
    CHECK_FALSE(Users::Password::hash("different", "salt") == password);
  }

  SECTION("Comparing to a string") {
    CHECK(password == "password");
    CHECK_FALSE(password == "different");
  }

  SECTION("Comparing a string to it") {
    CHECK("password" == password);
    CHECK_FALSE("different" == password);
  }
}
