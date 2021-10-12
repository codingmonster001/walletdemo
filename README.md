# walletdemo
android wallet demo based on web3j ( Stand-alone version )

# Scope of functionalities
## Register
Enter username and password to complete registration.
## Login
After registration, you can enter the username and password to complete login. 
## Create Wallet Address
You don’t have a wallet address after registration by default, you can create new wallet or import the private key of your own wallet.
## Import Wallet Private Key
You don’t have a wallet address after registration by default, you can create new wallet or import the private key of your own wallet.
## ETH Balance
After creating or importing wallet address, you can check the balance of eth in the wallet.
## Send ETH
You can send ETH from your wallet to other wallet.
## Switch Network
This app supports two networks, MainNet and Rinkeby Test Net.

# Sources
- Web3JService.java : Implements Web3JService class. This app complete blockchain operation by the object of this class.
- HomeActivity.java : Implements Home Activity. There are Login and Register Button on it.
- WalletActivity.java : Implemens Wallet Activity. There are some buttons for the app functions, the balance of ETH and the current ETH network.
- LoginActivity.java : Implements Login function.
- RegisterActivity.java : Implements Register function.
- GenerateWallet.java : Implements Generate wallet function.
- ImportKeyPrivateKey.java : Implements Private Key function.
- SwitchNetworkActivity.java : Implements switch the ETH blockchain network.
- SwitchWalletActivity.java : Displays the list of all wallets that imported and created in the device.
- SendActvity.java : Implemens sending ETH function.

# Technologies
Java, Android, Blockchain, Web3j
