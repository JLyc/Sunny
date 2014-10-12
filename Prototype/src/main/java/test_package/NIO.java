package test_package;

import static java.nio.file.StandardOpenOption.CREATE_NEW;
import static java.nio.file.StandardOpenOption.READ;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class NIO
{
	public static void main(String args[])
	{
		
		Path path = Paths.get("src/neural_center/WordPower.txt");
		
		try (SeekableByteChannel chanelTry = Files.newByteChannel(path,CREATE_NEW, READ))
		{
			ByteBuffer bufferTry = ByteBuffer.allocate(10);
			String encoding = System.getProperty("file.encoding");
			while (chanelTry.read(bufferTry) > 0)
			{
				bufferTry.rewind();
				//					System.out.println(bufferTry);
				System.out.print(Charset.forName(encoding).decode(bufferTry));
				bufferTry.flip();
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
			System.err.println("Fail :P");
		}
		
	}
	
}

