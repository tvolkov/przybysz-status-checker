# przybysz-status-checker

###Known issues:
openjdk-17 on macOS Ventura 13.0 gives this exception when trying to make requedt to  https://api-przybysz.duw.pl/
```
sun.security.provider.certpath.SunCertPathBuilderException: unable to find valid certification path to requested target
```

In order to fix this:
download certificates chain (duw-pl-chain.pem)
and install via
```
keytool -import -trustcacerts -alias duwplchain -file ~/Downloads/duw-pl-chain.pem -keystore ./cacerts
```