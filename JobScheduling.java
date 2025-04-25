import java.util.*;
class Job{
    int profit, deadline, number;

    Job(int profit, int deadline, int number){
        this.profit = profit;
        this.deadline = deadline;
        this.number = number;
    }

}

public class JobScheduling implements Comparator<Job>{

    public int compare(Job j1, Job j2){
        return j2.profit - j1.profit;
    }

    static int[] recJobScheduling(List<Job> jobs, int[] schedule, int profit, int timer){
        int[] temp;
        if(timer==jobs.size()-1||jobs.size()==0){
            return schedule;
        }
        int currProfit = profit;
        int bestJob = 0;
        for(Job job: jobs){
            if(job.deadline>timer){
                if(profit<currProfit+job.profit){
                    profit = currProfit+job.profit;
                    bestJob = job.number;
                }
            }
            else{
                jobs.remove(job);
            }
        }
        return null;
    }

    static int[] jobScheduling(List<Job> jobs){
        int[] schedule = new int[jobs.size()];
        for(int i=0;i<schedule.length;i++){
            schedule[i] = -1;
        }
        Collections.sort(jobs, new JobScheduling());
        return recJobScheduling(jobs, schedule, 0, 0);
    }
    
    public static void main(String[] args) {
        
    }
}