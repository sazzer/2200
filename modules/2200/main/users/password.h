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

  /**
   * Compare two password objects for equality
   * @param a The first password
   * @param b The second password
   * @return True if the two passwords are the same
   */
  bool operator==(const Password& a, const Password& b);
  /**
   * Compare a password to a plaintext version
   * @param a The hashed password
   * @param b The plaintext password
   * @return True if the two passwords are the same
   */
  bool operator==(const Password& a, const std::string& b);
  /**
   * Compare a password to a plaintext version
   * @param a The plaintext password
   * @param b The hashed password
   * @return True if the two passwords are the same
   */
  bool operator==(const std::string& a, const Password& b);
}
