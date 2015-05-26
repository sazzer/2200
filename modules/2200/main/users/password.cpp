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
}
