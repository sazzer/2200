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
     * @param seed The seed for the hash
     */
  Password(const std::string& hash, const std::string& seed)
    : hash_(hash),
      seed_(seed) {}
    /**
     * Get the hash
     * @return the hash
     */
    const std::string& hash() const {
      return hash_;
    }

    /**
     * Get the seed
     * @return the seed
     */
    const std::string& seed() const {
      return seed_;
    }
  protected:
  private:
    /** The actual password hash */
    const std::string hash_;
    /** The seed used to generate the hash */
    const std::string seed_;
  };
}