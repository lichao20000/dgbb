/**
 * author:Â¬±óêÍ
 * date:2011-03-02
 */
package com.dglt.comm.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.apache.tools.ant.DirectoryScanner;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.Copy;
import org.apache.tools.ant.taskdefs.Delete;
import org.apache.tools.ant.taskdefs.Jar;
import org.apache.tools.ant.taskdefs.Move;
import org.apache.tools.ant.taskdefs.Zip;
import org.apache.tools.ant.types.FileSet;
import org.apache.tools.ant.types.ZipScanner;

public class FileUtil
{
	public static final String FILE="FILE";
	public static final String FOLDER="FOLDER";
	public static final String HIDDEN_FILE="HIDDEN_FILE";
	public static final String HIDDEN_FOLDER="HIDDEN_FOLDER";
	public static final int DEFAULT_BUFFER_SIZE=1024;
	private static final Project PRJ=new Project(); 
	private byte[] buffer=null;
	
	public static File makeDir(String path)
	{
		File file=new File(path);
		file.mkdirs();
		return file;
	}
	
	public static String getAbsolutePath(String f)
	{
		if(f==null)
			return null;
		f=f.trim().replace("\\","/");
		File file=new File("");
		String absp=file.getAbsolutePath().replace("\\","/");
		while(true)
		{
			if(f.startsWith("../"))
			{
				f=f.substring(3);
				if(absp.lastIndexOf("/")==-1)
					break;
				absp=absp.substring(0,absp.lastIndexOf("/"));
				continue;
			}
			if(f.startsWith("./"))
			{
				f=f.substring(2);
				continue;
			}
			break;
		}
		if(f.charAt(0)!='/' && absp.endsWith("/")==false)
			f='/'+f;
		return absp+f;
	}
	
	public static String getParentFolder(String path)
	{
		if(path==null)
			return path;
		path=path.replace("\\","/");
		int index=path.lastIndexOf("/");
		if(index==-1)
			return path;
		path=path.substring(0,index);
		return path;
	}
	
	public static String getCurObjName(String path)
	{
		if(path==null)
			return null;
		path=path.replace("\\","/");
		int index=path.lastIndexOf("/");
		if(index==-1)
			return path;
		path=path.substring(index+1);
		return path;
	}
	
	public static String isExists(String file)
	{
		File _file=new File(file);
		if(_file.exists()==false)
			return null;
		if(_file.isDirectory())
		{
			if(_file.isHidden())
				return HIDDEN_FOLDER;
			else
				return FOLDER;
		}
		else
		{
			if(_file.isHidden())
				return HIDDEN_FILE;
			else
				return FILE;
		}
	}

	public static String isExists(File _file)
	{
		if(_file.exists()==false)
			return null;
		if(_file.isDirectory())
		{
			if(_file.isHidden())
				return HIDDEN_FOLDER;
			else
				return FOLDER;
		}
		else
		{
			if(_file.isHidden())
				return HIDDEN_FILE;
			else
				return FILE;
		}
	}
	
	public File copy(String from,String to)throws Exception
	{
		if(from==null || to==null || from.equals(to))
			return null;
		from=from.replace("\\","/");
		to=to.replace("\\","/");
		return copy(from,to,FileUtil.DEFAULT_BUFFER_SIZE);
	}
	
	public File copy(String from,String to,int bufferSize)throws Exception
	{
		if(from==null || to==null || from.equals(to))
			return null;
		from=from.replace("\\","/");
		File fromFile=new File(from);
		if(fromFile.exists()==false)
			return null;
		to=to.replace("\\","/");
		File toFile=new File(to);
		copy(fromFile,toFile,bufferSize);
		return toFile;
	}
	
	public File copy(File from,File to)throws Exception
	{
		return copy(from,to,FileUtil.DEFAULT_BUFFER_SIZE);
	}
	
	public File copy(File from,File to,int bufferSize)throws Exception
	{
		if(from==null || to==null || from.getAbsolutePath().equals(to.getAbsolutePath()))
			return null;
		if(from.exists()==false)
			return null;
		if(from.isFile())
			copyFile(from,to,bufferSize);
		else
			copyDir(from,to,bufferSize);
		return to;
	}
	
	private File copyFile(File from,File to,int bufferSize)throws Exception
	{
		if(bufferSize<1)
			bufferSize=FileUtil.DEFAULT_BUFFER_SIZE;
		if(buffer==null)
			buffer = new byte[bufferSize];
		if(to.exists()==false)
			to.mkdirs();
		File destFile=new File(to.getAbsoluteFile()+"/"+from.getName());
    	InputStream srcFileStream = new FileInputStream(from);
    	OutputStream destFileStream = new FileOutputStream(destFile);
    	int bytes_read;
    	while((bytes_read = srcFileStream.read(buffer))!=-1)
    	{
    		destFileStream.write(buffer,0,bytes_read);
    	}
    	try
    	{
    		srcFileStream.close();
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    	try
    	{
    		destFileStream.close();
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
		return to;
	}
	
	private File copyDir(File from,File to,int bufferSize)throws Exception
	{
		File desDir=new File(to+"/"+from.getName());
		File[] list=from.listFiles();
		for(int i=0;i<list.length;i++)
		{
			if(list[i].isFile())
			{
				copyFile(list[i],desDir,bufferSize);
			}
			else
				copyDir(list[i],desDir,bufferSize);
		}
		return to;
	}
	
	public File delete(String file)
	{
		if(file==null || file.length()==0)
			return null;
		File _file=new File(file);
		return this.delete(_file);
	}
	
	public File delete(File _file)
	{
		if(_file.isDirectory())
		{
			if(_file.exists()==false)
				return null;
			delDir(_file);
		}
		else
		{
			String fileName=_file.getName();
			String[] suffix=fileName.split("[.]");
			if(suffix[0].equals("*"))
			{
				delFile(_file.getParentFile(),"."+suffix[1]);
			}
			else
			{
				if(suffix[0].equals("**"))
				{
					delFileRecursion(_file.getParentFile(),"."+suffix[1]);
				}
				else
				{
					_file.delete();
				}
			}
		}
		return _file;
	}

	private File delDir(File currentDir)
	{
		File[] list=currentDir.listFiles();
		for(int i=0;i<list.length;i++)
		{
			if(list[i].isDirectory())
				delDir(list[i]);
			else
				list[i].delete();
		}
		currentDir.delete();
		return currentDir;
	}
	
	private File delFileRecursion(File file,String suffix)
	{
		File[] list=file.listFiles();
		for(int i=0;i<list.length;i++)
		{
			if(list[i].isDirectory())
				delFileRecursion(list[i],suffix);
			else
			{
				if(list[i].getName().endsWith(suffix))
					list[i].delete();
			}
		}
		return file;
	}
	
	private File delFile(File file,String suffix)
	{
		File[] list=file.listFiles();
		for(int i=0;i<list.length;i++)
		{
			if(list[i].isFile() && list[i].getName().endsWith(suffix))
				list[i].delete();
		}
		return file;
	}
	
	public File delete(String file,String include,String exclude)
	{
		if(file==null || file.length()==0)
			return null;
		return this.delete(new File(file), include, exclude);
	}
	
	public File delete(File file,String include,String exclude)
	{
		if(file==null || file.exists()==false)
			return null;
		Delete delete=new Delete();
		delete.setProject(PRJ);
		if(file.isDirectory())
		{
			delete.setDir(file);
			if(include!=null && include.length()>0)
				delete.setIncludes(include);
			if(exclude!=null && exclude.length()>0)
				delete.setExcludes(exclude);
		}
		else
		{
			delete.setFile(file);
		}
		delete.execute();
		return file;
	}
	
	public File rename(String file,String newName)
	{
		if(file==null || newName==null || file.equals(newName))
			return null;
		File _file=new File(file);
		if(_file.exists()==false)
			return null;
		newName=FileUtil.getParentFolder(file)+"/"+newName;
		File newFile=new File(newName);
		_file.renameTo(newFile);
		return newFile;
	}
	
	public File move(String from,String to,String include,String exclude,boolean includeEmptyDir)
	{
		if(from==null || to==null || from.equals(to))
			return null;
		File _from=new File(from);
		if(_from.exists()==false)
			return null;
		return move(_from,new File(to),include,exclude,includeEmptyDir);
	}
	
	public File move(File from,File to,String include,String exclude,boolean includeEmptyDir)
	{
		if(from==null || to==null || from.getAbsolutePath().equals(to.getAbsolutePath()) || from.exists()==false)
			return null;
		Move move=new Move();
		move.setIncludeEmptyDirs(includeEmptyDir);
		move.setProject(PRJ); 
		move.setTodir(to);
		if(from.isDirectory())
		{
			FileSet fs=new FileSet(); 
			fs.setProject(PRJ); 
			fs.setDir(from);
			if(include==null || include.length()==0)
				fs.setIncludes("**/*.*"); //????????ä»?
			else
				fs.setIncludes(include);
			if(exclude!=null && exclude.length()>0)
				fs.setExcludes(exclude); //???CVS?¸å???»¶ï¼?»¥??.class??»¶
			move.addFileset(fs);
		}
		else
		{
			move.setFile(from);
		}
		move.execute();
		return to;
	}
	
	public File copy(String from,String to,String include,String exclude,boolean flatPattern)
	{
		if(from==null || to==null || from.equals(to))
			return null;
		File _from=new File(from);
		if(_from.exists()==false)
			return null;
		return copy(_from,new File(to),include,exclude,flatPattern);
	}
	
	public File copy(File from,File to,String include,String exclude,boolean flatPattern)
	{
		if(from==null || to==null || from.getAbsolutePath().equals(to.getAbsolutePath()) || from.exists()==false)
			return null;
		Copy copy=new Copy(); 
		copy.setProject(PRJ); 
		copy.setTodir(to); 
		FileSet fs=new FileSet(); 
		fs.setProject(PRJ); 
		fs.setDir(from);
		if(include==null || include.length()==0)
			fs.setIncludes("**/*.*"); //????????ä»?
		else
			fs.setIncludes(include);
		if(exclude!=null && exclude.length()>0)
			fs.setExcludes(exclude); //???CVS?¸å???»¶ï¼?»¥??.class??»¶
		copy.addFileset(fs);
		copy.setFlatten(flatPattern); 
		copy.execute();
		return to;
	}
	
	public File emptyDir(String file)
	{
		if(file==null || file.length()==0)
			return null;
		File _dir=new File(file);
		return emptyDir(_dir);
	}
	
	public File emptyDir(File _dir)
	{
		if(_dir==null || _dir.exists()==false || _dir.isDirectory()==false)
			return null;
		File[] list=_dir.listFiles();
		for(int i=0;i<list.length;i++)
		{
			this.delete(list[i]);
		}
		return _dir;
	}
	
	public String[] fileScan(String baseDir,String[] include,String[] exclude)
	{
		if(baseDir==null || baseDir.length()==0)
			return null;
		return fileScan(new File(baseDir),include,exclude);
	}

	public String[] fileScan(File file,String[] include,String[] exclude)
	{
		if(file==null || file.exists()==false)
			return null;
		if(file.isDirectory())
		{
			DirectoryScanner ds=new DirectoryScanner(); 
			ds.setBasedir(file); 
			if(include!=null && include.length>0)
				ds.setIncludes(include);
			if(exclude!=null && exclude.length>0)
				ds.setExcludes(exclude);
			ds.scan();
			return ds.getIncludedFiles();
		}
		if(file.isFile())
		{
			String fileName=file.getName().toLowerCase();
			if(fileName.endsWith(".zip") || fileName.endsWith(".jar"))
			{
				ZipScanner scan=new ZipScanner();
				scan.setSrc(file);
				if(include!=null && include.length>0)
					scan.setIncludes(include);
				if(exclude!=null && exclude.length>0)
					scan.setExcludes(exclude);
				scan.scan();
				return scan.getIncludedFiles();
			}
		}
		return null;
	}
	
	public File zip(String srcFile,String destFile,String include,String exclude)
	{
		if(srcFile==null || srcFile.length()==0 || destFile==null || destFile.length()==0 || srcFile.equals(destFile))
			return null;
		File _srcFile=new File(srcFile);
		File _destFile=new File(destFile);
		return zip(_srcFile,_destFile,include,exclude);
	}
	
	public File zip(File srcFile,File destFile,String include,String exclude)
	{
		if(srcFile.exists()==false)
			return null;
		Zip zip=new Zip();
		zip.setProject(PRJ);
		zip.setDestFile(destFile);
		if(srcFile.isDirectory())
		{
			FileSet fileSet=new FileSet();
			fileSet.setProject(PRJ);
			fileSet.setDir(srcFile);
			if(include!=null && include.length()>0)
				fileSet.setIncludes(include);
			if(exclude!=null && exclude.length()>0)
				fileSet.setExcludes(exclude);
			zip.addFileset(fileSet);
		}
		else
		{
			zip.setBasedir(srcFile.getParentFile());
			zip.setIncludes(srcFile.getName());
		}
		zip.execute();
		return destFile;
	}
	
	public File jar(String srcFile,String destFile,String include,String exclude)
	{
		if(srcFile==null || srcFile.length()==0 || destFile==null || destFile.length()==0 || srcFile.equals(destFile))
			return null;
		File _srcFile=new File(srcFile);
		File _destFile=new File(destFile);
		return zip(_srcFile,_destFile,include,exclude);
	}
	
	public File jar(File srcFile,File destFile,String include,String exclude)
	{
		if(srcFile.exists()==false)
			return null;
		Jar jar=new Jar();
		jar.setProject(PRJ);
		jar.setDestFile(destFile);
		if(srcFile.isDirectory())
		{
			FileSet fileSet=new FileSet();
			fileSet.setProject(PRJ);
			fileSet.setDir(srcFile);
			if(include!=null && include.length()>0)
				fileSet.setIncludes(include);
			if(exclude!=null && exclude.length()>0)
				fileSet.setExcludes(exclude);
			jar.addFileset(fileSet);
		}
		else
		{
			jar.setBasedir(srcFile.getParentFile());
			jar.setIncludes(srcFile.getName());
		}
		jar.execute();
		return destFile;
	}
	
	/*
	public File expand(String srcFile,String destDir,String include,String exclude,boolean overWrite)
	{
		if(srcFile==null || srcFile.length()==0 || destDir==null || destDir.length()==0 || srcFile.equals(destDir))
			return null;
		File _srcFile=new File(srcFile);
		File _destDir=new File(destDir);
		return this.expand(_srcFile,_destDir, include, exclude,overWrite);
	}
	
	public File expand(File srcFile,File destDir,String include,String exclude,boolean overWrite)
	{
		if(srcFile.exists()==false || srcFile.isDirectory() || srcFile.getAbsolutePath().equals(destDir.getAbsolutePath()))
			return null;
		String fileName=srcFile.getName().toLowerCase();
		if(fileName.endsWith(".zip") || fileName.endsWith(".jar"))
		{
			Expand expand=new Expand();
			expand.setProject(PRJ);
			expand.setSrc(srcFile);
			expand.setOverwrite(overWrite);
			expand.setDest(destDir);
			if((include!=null && include.length()>0) || (exclude!=null && exclude.length()>0))
			{
				PatternSet patternset=new PatternSet();
				patternset.setProject(PRJ);
				if(include!=null && include.length()>0)
					patternset.setIncludes(include);
				if(exclude!=null && exclude.length()>0)
					patternset.setExcludes(exclude);
				expand.addPatternset(patternset);
			}
			expand.execute();
		}
		else
		{
			if(fileName.endsWith(".rar"))
			{
				String path=destDir.getAbsolutePath();
				File temp;
				FileOutputStream os=null;
				try
				{
			        Archive rar=new Archive(srcFile);
			        FileHeader fh=rar.nextFileHeader();
			        while(fh != null)
			        {
			        	if(fh.isDirectory()==false)
			        	{
			        		temp=new File(path+"/"+fh.getFileNameString());
			        		if(temp.exists()==false || overWrite==true)
			        		{
				        		temp.getParentFile().mkdirs();
					            os=new FileOutputStream(temp);
					            rar.extractFile(fh,os);
					            os.close();
			        		}
			        	}
			        	fh=rar.nextFileHeader();
			        }
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				finally
				{
					try
					{
						if(os!=null)
							os.close();
					}
					catch(Exception e){e.printStackTrace();}
				}
			}
		}
		return destDir;
	}
	*/
	public void readZip(String file)throws Exception
	{
		ZipFile zipfile=new ZipFile(new File(file));
		for (Enumeration entries = zipfile.entries(); entries.hasMoreElements();)
		{ 
			ZipEntry entry = (ZipEntry) entries.nextElement();
			if(entry.isDirectory()) 
				System.out.println("Directory: "+entry.getName()); 
			else 
				System.out.println("file: "+entry.getName()); 
		}
		zipfile.close();
	}
	
	public void readJar(String file)throws Exception
	{
		JarFile jarfile=new JarFile(new File(file));
		for (Enumeration entries = jarfile.entries(); entries.hasMoreElements();)
		{ 
			ZipEntry entry = (ZipEntry) entries.nextElement();
			if(entry.isDirectory()) 
				System.out.println("Directory: "+entry.getName()); 
			else 
				System.out.println("file: "+entry.getName()); 
		}
		jarfile.close();
	}

	/**
	 * È¡µÃÎÄ¼þÃûºó×º
	 * @param fileName	ÎÄ¼þÃû
	 * @param spliter	·Ö¸ô·û
	 * @return	·µ»Øºó×º£¬²»°üº¬·Ö¸ô·û
	 * @author Â¬±óêÍ
	 * @since Mar 4, 2011 10:20:04 AM
	 */
	public static String getFileSuffix(String fileName,String spliter)
	{
		fileName=StringUtil.defaultString(fileName,null);
		if(null == fileName)
			return null;
		spliter=StringUtil.defaultString(spliter,".");
		if(null == spliter)
			return null;
		if(fileName.endsWith(spliter))
			return null;
		int lastIndex=fileName.lastIndexOf(spliter);
		if(-1 == lastIndex)
			return null;
		return fileName.substring(lastIndex+spliter.length());
	}
}