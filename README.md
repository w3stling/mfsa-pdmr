MFSA PDMR registry
==================

[![Build Status](https://travis-ci.org/w3stling/mfsa-pdmr.svg?branch=master)](https://travis-ci.org/w3stling/mfsa-pdmr)
[![Download](https://api.bintray.com/packages/apptastic/maven-repo/mfsapdmr/images/download.svg)](https://bintray.com/apptastic/maven-repo/mfsapdmr/_latestVersion)
[![Javadoc](https://img.shields.io/badge/javadoc-1.0.4-blue.svg)](https://w3stling.github.io/mfsa-pdmr/javadoc/1.0.4)
[![License](http://img.shields.io/:license-MIT-blue.svg?style=flat-round)](http://apptastic-software.mit-license.org)   
[![Quality Gate](https://sonarcloud.io/api/project_badges/measure?project=com.apptastic%3Amfsapdmr&metric=alert_status)](https://sonarcloud.io/dashboard?id=com.apptastic%3Amfsapdmr)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=com.apptastic%3Amfsapdmr&metric=coverage)](https://sonarcloud.io/component_measures?id=com.apptastic%3Amfsapdmr&metric=Coverage)
[![Bugs](https://sonarcloud.io/api/project_badges/measure?project=com.apptastic%3Amfsapdmr&metric=bugs)](https://sonarcloud.io/component_measures?id=com.apptastic%3Amfsapdmr&metric=bugs)
[![Vulnerabilities](https://sonarcloud.io/api/project_badges/measure?project=com.apptastic%3Amfsapdmr&metric=vulnerabilities)](https://sonarcloud.io/component_measures?id=com.apptastic%3Amfsapdmr&metric=vulnerabilities)
[![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=com.apptastic%3Amfsapdmr&metric=code_smells)](https://sonarcloud.io/component_measures?id=com.apptastic%3Amfsapdmr&metric=code_smells)

Person Discharging Managerial Responsibility (PDMR) transaction registry for [Malta Financial Services Authority][1] (MFSA)

This Java library makes it easier to automate data extraction from MFSA PDMR registry.

Examples
--------
Get PDMR transactions
```java
PdmrRegistry pdmrRegistry = new PdmrRegistry();
List<Transaction> transactions = pdmrRegistry.getTransactions()
                                              .collect(Collectors.toList());
```


Download
--------

Download [the latest JAR][2] or grab via [Maven][3] or [Gradle][4].

### Maven setup
Add JCenter repository for resolving artifact:
```xml
<project>
    ...
    <repositories>
        <repository>
            <id>jcenter</id>
            <url>https://jcenter.bintray.com</url>
        </repository>
    </repositories>
    ...
</project>
```

Add dependency declaration:
```xml
<project>
    ...
    <dependencies>
        <dependency>
            <groupId>com.apptastic</groupId>
            <artifactId>mfsapdmr</artifactId>
            <version>1.0.4</version>
        </dependency>
    </dependencies>
    ...
</project>
```

### Gradle setup
Add JCenter repository for resolving artifact:
```groovy
repositories {
    jcenter()
}
```

Add dependency declaration:
```groovy
dependencies {
    implementation 'com.apptastic:mfsapdmr:1.0.4'
}
```

MFSA PDMR library requires at minimum Java 11.

License
-------

    MIT License
    
    Copyright (c) 2019, Apptastic Software
    
    Permission is hereby granted, free of charge, to any person obtaining a copy
    of this software and associated documentation files (the "Software"), to deal
    in the Software without restriction, including without limitation the rights
    to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
    copies of the Software, and to permit persons to whom the Software is
    furnished to do so, subject to the following conditions:
    
    The above copyright notice and this permission notice shall be included in all
    copies or substantial portions of the Software.
    
    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
    IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
    FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
    AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
    LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
    OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
    SOFTWARE.


[1]: https://www.mfsa.mt
[2]: https://bintray.com/apptastic/maven-repo/mfsa-pdmr/_latestVersion
[3]: https://maven.apache.org
[4]: https://gradle.org