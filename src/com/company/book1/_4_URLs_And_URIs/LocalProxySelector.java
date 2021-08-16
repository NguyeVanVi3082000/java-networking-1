package com.company.book1._4_URLs_And_URIs;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

public class LocalProxySelector extends ProxySelector {

    List<URI> failed = new ArrayList<>();

    @Override
    public List<Proxy> select(URI uri) {
        List<Proxy> result = new ArrayList<>();
        if (failed.contains(uri) || !"http".equalsIgnoreCase(uri.getScheme()))
            result.add(Proxy.NO_PROXY);
        else
        {
            SocketAddress httpProxy = new InetSocketAddress("proxy.example.com", 80);
            Proxy proxy = new Proxy(Proxy.Type.HTTP, httpProxy);
            result.add(proxy);
        }
        return result;

    }

    @Override
    public void connectFailed(URI uri, SocketAddress sa, IOException ioe) {
        failed.add(uri);
    }
}
