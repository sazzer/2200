#include "password.h"
#include <boost/uuid/uuid.hpp>
#include <boost/uuid/uuid_generators.hpp>
#include <boost/uuid/uuid_io.hpp>
#include <openssl/sha.h>

/** The UUID Generator to use */
static boost::uuids::random_generator uuidGenerator;

namespace Users {
  Password Password::hash(const std::string& password) {
    boost::uuids::uuid uuid = uuidGenerator();
    
    return Password::hash(password, boost::uuids::to_string(uuid));
  }
  
  Password Password::hash(const std::string& password, const std::string& salt) {
    unsigned char hash[SHA256_DIGEST_LENGTH];

    SHA256_CTX sha256;
    SHA256_Init(&sha256);
    SHA256_Update(&sha256, salt.c_str(), salt.size());
    SHA256_Update(&sha256, password.c_str(), password.size());
    SHA256_Final(hash, &sha256);

    std::string hashedPassword;
    char bytePair[2];
    for (unsigned char h : hash) {
      sprintf(bytePair, "%02x", h);
      hashedPassword += bytePair;
    }
    
    return Password(hashedPassword, salt);
  }
  
  /**
   * Compare two password objects for equality
   * @param a The first password
   * @param b The second password
   * @return True if the two passwords are the same
   */
  bool operator==(const Password& a, const Password& b) {
    return a.hash() == b.hash() && a.salt() == b.salt();
  }
  
  /**
   * Compare a password to a plaintext version
   * @param a The hashed password
   * @param b The plaintext password
   * @return True if the two passwords are the same
   */
  bool operator==(const Password& a, const std::string& b) {
    return Password::hash(b, a.salt()) == a;
  }
  
  /**
   * Compare a password to a plaintext version
   * @param a The plaintext password
   * @param b The hashed password
   * @return True if the two passwords are the same
   */
  bool operator==(const std::string& a, const Password& b) {
    return b == a;
  }
}
