package src.com.besttone.thread;

public class ThreadWorks {
	class Work{
		int i = 0;
		Work(int i){
			this.i = i;
		}
		private void doWork(){
			System.out.println("当前正在处理第"+i+"个任务...");
		}
	}
	
	class Processor implements Runnable{
		int j = 0;
		Work work;
		Processor(int j,Work work){
			this.j = j;
			this.work = work;
		}
		public void run() {
			System.out.println("当前线程"+j);
			work.doWork();
		}
		
	}
	
	public void process(int w,int p) throws InterruptedException{
		int wp = w/p;
		int wc = 0;
		for(int i=0;i<p;i++){
			int count = 1;
			Work work = null;
			Thread t = null;
			for(int j=wc;j<w;j++){
				if(count>wp){
					break;
				}
				work = new Work(j);
				Processor p1 = new Processor(i,work);
				t = new Thread(p1);
				t.start();
				count++;
			}
			wc = wp*(i+1);
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		ThreadWorks tw = new ThreadWorks();
		tw.process(20, 10);
	}
}
