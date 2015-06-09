/**
 * 
 */
package patlas.agh.utils;

import patlas.agh.Downloader;

/**
 * @author PatLas
 *
 */
public interface ThreadCompleteListener 
{
	void notifyOfThreadComplete(final Downloader downloader);
}
