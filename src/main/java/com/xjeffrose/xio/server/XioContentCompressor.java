package com.xjeffrose.xio.server;


import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import io.netty.handler.codec.http.DefaultHttpContent;
import io.netty.handler.codec.http.HttpContentCompressor;
import org.apache.log4j.Logger;

public class XioContentCompressor extends HttpContentCompressor {

    private static final Logger log = Logger.getLogger(XioContentCompressor.class.getName());
    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {

        log.info("#####****** compressor: the msg class: " + msg.getClass().getName() );
        if (msg instanceof ByteBuf) {
            // convert ByteBuf to HttpContent to make it work with compression. This is needed as we use the
            // ChunkedWriteHandler to send files when compression is enabled.
            msg =  new DefaultHttpContent((ByteBuf) msg);
        }
        super.write(ctx, msg, promise);
    }
}
