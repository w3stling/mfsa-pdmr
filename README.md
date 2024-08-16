MFSA PDMR registry
==================

[![Build](https://github.com/w3stling/mfsa-pdmr/actions/workflows/build.yml/badge.svg)](https://github.com/w3stling/mfsa-pdmr/actions/workflows/build.yml)
[![Download](https://img.shields.io/badge/download-2.0.5-brightgreen.svg)](https://central.sonatype.com/artifact/com.apptasticsoftware/mfsapdmr/2.0.5/overview)
[![Javadoc](https://img.shields.io/badge/javadoc-2.0.5-blue.svg)](https://w3stling.github.io/mfsa-pdmr/javadoc/2.0.5)
[![License](http://img.shields.io/:license-MIT-blue.svg?style=flat-round)](http://apptastic-software.mit-license.org)   
[![CodeQL](https://github.com/w3stling/mfsa-pdmr/actions/workflows/codeql-analysis.yml/badge.svg)](https://github.com/w3stling/mfsa-pdmr/actions/workflows/codeql-analysis.yml)
[![Quality Gate](https://sonarcloud.io/api/project_badges/measure?project=w3stling_mfsa-pdmr&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=w3stling_mfsa-pdmr)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=w3stling_mfsa-pdmr&metric=coverage)](https://sonarcloud.io/summary/new_code?id=w3stling_mfsa-pdmr)
[![Bugs](https://sonarcloud.io/api/project_badges/measure?project=w3stling_mfsa-pdmr&metric=bugs)](https://sonarcloud.io/summary/new_code?id=w3stling_mfsa-pdmr)
[![Vulnerabilities](https://sonarcloud.io/api/project_badges/measure?project=w3stling_mfsa-pdmr&metric=vulnerabilities)](https://sonarcloud.io/summary/new_code?id=w3stling_mfsa-pdmr)
[![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=w3stling_mfsa-pdmr&metric=code_smells)](https://sonarcloud.io/summary/new_code?id=w3stling_mfsa-pdmr)

> **Note** - from version 2.0.0:
> * New Java package name
> * New group ID in Maven / Gradle dependency declaration
> * Moved repository from `JCenter` to `Maven Central Repository`

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
Add dependency declaration:
```xml
<project>
    ...
    <dependencies>
        <dependency>
            <groupId>com.apptasticsoftware</groupId>
            <artifactId>mfsapdmr</artifactId>
            <version>2.0.5</version>
        </dependency>
    </dependencies>
    ...
</project>
```

### Gradle setup
Add dependency declaration:
```groovy
dependencies {
    implementation 'com.apptasticsoftware:mfsapdmr:2.0.5'
}
```

MFSA PDMR library requires at minimum Java 11.

License
-------

    MIT License
    
    Copyright (c) 2024, Apptastic Software
    
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
[2]: https://central.sonatype.com/artifact/com.apptasticsoftware/mfsapdmr/2.0.5/overview
[3]: https://maven.apache.org
[4]: https://gradle.org