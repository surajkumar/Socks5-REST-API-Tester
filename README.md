# Socks5

## Introduction
A Socks5 proxy is similar to a VPN wherere you can send and receives requests using a different server. The main difference here is that a Socks5 proxy does not encrypt traffic and more so relays packets that you send and receive so that your IP address remains hidden.

When testing network services such a [REST API](https://en.wikipedia.org/wiki/Representational_state_transfer) we can use Socks5 proxys to test how well our server responds to real-world loads and connections.

### Benifits
* No authentication required to use a Socks5 proxy
* Unlike a VPN you can use a UDP socket
* Better latency with smaller packet sizes
* Free to use - no authentication and tons of publicly available servers available

This repository contains 290 open Socks5 servers that are available for use. The code has been created so that you can plugin your own code directly into ours and start using the Socks5 proxys right away. Though, the code is directed for usage on HTTP servers, you can modify this to work with TCP/UDP sockets as well as web sockets.

## Requirements
* Java 8 or higher

## Usage
See below sample code:
```
    public static void main(String[] args) throws IOException {
        Socks5 socks5 = new Socks5();

        /* Ignore any SSL warnings */
        socks5.trustAllSSL();

        /* Load our proxy list */
        socks5.loadProxyFile("socks5.txt");

        /* Or we can manually add a proxy to the list */
        socks5.add("213.251.238.186:9050");

        ExampleSocks5 exampleSocks5 = new ExampleSocks5();

        /*
         * Call our example REST API call 10 times for each proxy.
         * Since there are 290 registered proxies, all 290 servers will run our request
         * 10 times sequentially.
         */
        socks5.run(10, exampleSocks5);
    }
```

## Contributing
Contributions are welcome whether it is for small bug fixes or new pieces of major functionality. To contribute changes, you should first fork the upstream repository to your own GitHub account. You can then add a new remote for `upstream` and rebase any changes you make and keep up-to-date with the upstream.

`git remote add upstream https://github.com/surajkumar/Socks5.git`
