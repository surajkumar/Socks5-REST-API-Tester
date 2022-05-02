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
* Java 7 or higher

## Usage
To be documented.

## Contributing
Contributions are welcome whether it is for small bug fixes or new pieces of major functionality. To contribute changes, you should first fork the upstream repository to your own GitHub account. You can then add a new remote for `upstream` and rebase any changes you make and keep up-to-date with the upstream.

`git remote add upstream https://github.com/surajkumar/Socks5.git`
