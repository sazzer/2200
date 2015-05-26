#include <string>

namespace Users {
  /**
   * Representation of a hashed password
   */
  class Password {
  public:
    /**
     * Construct the password
     * @param hash The hashed password
     * @param salt The salt for the hash
     */
  Password(const std::string& hash, const std::string& salt)
    : hash_(hash),
      salt_(salt) {}
    /**
     * Get the hash
     * @return the hash
     */
    const std::string& hash() const {
      return hash_;
    }

    /**
     * Get the salt
     * @return the salt
     */
    const std::string& salt() const {
      return salt_;
    }

    /**
     * Hash the given plain-text password, generating a salt
     * @param password The password
     * @return the hashed password
     */
    static Password hash(const std::string& password);

    /**
     * Hash the given plain-text password, using the provided salt
     * @param password The password
     * @param salt The salt
     * @return the hashed password
     */
    static Password hash(const std::string& password, const std::string& salt);
  protected:
  private:
    /** The actual password hash */
    const std::string hash_;
    /** The salt used to generate the hash */
    const std::string salt_;
  };
}
