/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author tangyu
 */
import java.awt.Component;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

//通过本次实验，知晓两个问题：1.repaint()方法不能在主方法(主类)中使用(否则会引起背景透明问题)；2.默认布局会引起组件覆盖问题。
//bug1:如果非第一次执行开始模拟按钮index值为零,程序会直接退出;
//bug2：当产品为零时，点恢复模拟，因为生产者较快。仓库第一格会没有产品.
//bug3:生产速度必须大于等于生产速度；
//如果有大神解决了BUG请回传给我谢谢
public class ConsumerAndProducer extends JFrame {

    private static final long serialVersionUID = 1L;
    public static JLabel jl5 = new JLabel();
    public static int index = 0;
    Icon icon = new ImageIcon("E://OS experiment//22.jpg");
    public static JLabel jl6 = new JLabel("jl6");
    public static JLabel jl8 = new JLabel("jl8");
    public static JLabel jl7 = new JLabel("jl7");
    public static JLabel jl9 = new JLabel("jl9");
    public static JLabel jl10 = new JLabel("jl10");
    public static JLabel jl11 = new JLabel("jl11");
    public static JLabel jl12 = new JLabel("jl12");
    public static JLabel jl13 = new JLabel("jl13");
    public static JLabel jl14 = new JLabel("jl14");
    public static JLabel jlabel;
    public static JLabel mark;//为生产按钮设置标记
    public static JLabel mark1;//为消费按钮设置标记
    Consumer test = new Consumer();
    Producer test2 = new Producer();
    Container ff = this.getContentPane();

    public ConsumerAndProducer(String title) {
        super(title);
        ff.add(test);
        ff.add(test2);
        setBounds(200, 200, 900, 653);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        jl5.setBounds(620, 40, 200, 200);
        jl5.setBorder(BorderFactory.createEtchedBorder());
        jl5.setLayout(new GridLayout(3, 3));
        add(jl5);

        jlabel = new JLabel("消息显示器");
        jlabel.setBounds(2, 500, 390, 100);
        jlabel.setBorder(BorderFactory.createEtchedBorder());
        add(jlabel);
        mark = new JLabel("生产进行中");
        mark1 = new JLabel("消费进行中");
        add(mark);
        add(mark1);

        jl6.setSize(20, 20);
        jl7.setSize(20, 20);
        jl8.setSize(20, 20);
        jl9.setSize(20, 20);
        jl10.setSize(20, 20);
        jl11.setSize(20, 20);
        jl12.setSize(20, 20);
        jl13.setSize(20, 20);
        jl14.setSize(20, 20);
        jl5.add(jl6);
        jl5.add(jl7);
        jl5.add(jl8);
        jl5.add(jl9);
        jl5.add(jl10);
        jl5.add(jl11);
        jl5.add(jl12);
        jl5.add(jl13);
        jl5.add(jl14);
        jl6.setBorder(BorderFactory.createEtchedBorder());
        jl7.setBorder(BorderFactory.createEtchedBorder());
        jl8.setBorder(BorderFactory.createEtchedBorder());
        jl9.setBorder(BorderFactory.createEtchedBorder());
        jl10.setBorder(BorderFactory.createEtchedBorder());
        jl11.setBorder(BorderFactory.createEtchedBorder());
        jl12.setBorder(BorderFactory.createEtchedBorder());
        jl13.setBorder(BorderFactory.createEtchedBorder());
        jl14.setBorder(BorderFactory.createEtchedBorder());

    }

    public static void main(String args[]) {
        ConsumerAndProducer frame = new ConsumerAndProducer("模拟消费者生产者实验");
        frame.setVisible(true);
        frame.setLayout(null);
        Thread3 tt = new Thread3(frame);
        tt.start();

    }
}

//生产者线程
class Producer extends JPanel {

    private static final long serialVersionUID = 1L;
    public static Thread1 dd1 = new Thread1();
    public static ImageIcon icon;
    public static int x = 0, y = 60;
    JLabel jl3;
    Image icon1;
    JButton jb3, jb4;

    public Producer() {
        setLayout(null);
        this.setBounds(38, 78, 320, 270);
        icon = new ImageIcon("E://OS experiment//22.jpg");
        icon1 = icon.getImage();
        final JButton jb3 = new JButton("开始消费");
        final JButton jb4 = new JButton("停止消费");
        JLabel jl6 = new JLabel("模拟消费者");
        jl6.setBounds(125, 5, 75, 30);
        this.add(jl6);
        jl6.setBorder(BorderFactory.createEtchedBorder());
        this.add(jb3);
        this.add(jb4);
        jb3.setBounds(45, 146, 120, 50);
        jb4.setBounds(175, 146, 120, 50);
        jb4.addActionListener(new ActionListener() {
            @SuppressWarnings("deprecation")
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == jb4 && e.getActionCommand().equals("停止消费")) {
                    dd1.suspend();
                    ConsumerAndProducer.mark1.setText("消费已停止");
                }
            }
        });
        jb3.addActionListener(new ActionListener() {
            @SuppressWarnings("deprecation")
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == jb3 && e.getActionCommand().equals("开始消费")) {
                    if (ConsumerAndProducer.index < 1) {
                        dd1.suspend();
                        ConsumerAndProducer.jlabel.setText("仓库里没有产品或已经消费完毕！");
                        ConsumerAndProducer.mark1.setText("消费进行中");
                    }
                    dd1.resume();
                    return;
                }

            }

        });

    }

    public void paint(Graphics g) {
        if (x > getWidth()) {
            x = -50;

            if (ConsumerAndProducer.mark.getText() == "生产进行中") {
                Consumer.dd.resume();
            }

            if (ConsumerAndProducer.index < 2) {
                dd1.suspend();
                //		Consumer.dd.suspend();
            }
            Component[] labels = ConsumerAndProducer.jl5.getComponents();
            ((JLabel) labels[--ConsumerAndProducer.index]).setIcon(null);

            if (ConsumerAndProducer.index == 0) {
                ConsumerAndProducer.jlabel.setText("仓库产品已消费完毕，请生产产品或者恢复模拟！");
            }
            if (ConsumerAndProducer.index != 0) {
                ConsumerAndProducer.jlabel.setText("消费状态下当前仓库产品数量为：" + ConsumerAndProducer.index);
            }
        }
        super.paint(g);
        g.drawImage(icon1, x, y, jl3);
        repaint();
    }
}


//消费者线程
class Consumer extends JPanel {

    private static final long serialVersionUID = 1L;
    public static Thread2 dd = new Thread2();
    public static ImageIcon icon;
    public static int x = 0, y = 60;
    JLabel jl1;
    Image icon1;
    JButton jb1, jb2;

    public Consumer() {
        setLayout(null);
        this.setBounds(420, 400, 320, 440);
        icon = new ImageIcon("E://OS experiment//22.jpg");
        icon1 = icon.getImage();
        final JButton jb1 = new JButton("开始生产");
        final JButton jb2 = new JButton("停止生产");
        JLabel jl3 = new JLabel("模拟生产者");
        jl3.setBounds(125, 5, 75, 30);
        this.add(jl3);
        jl3.setBorder(BorderFactory.createEtchedBorder());
        this.add(jb1);
        this.add(jb2);
        jb1.setBounds(45, 146, 120, 50);
        jb2.setBounds(175, 146, 120, 50);
        jb2.addActionListener(new ActionListener() {
            @SuppressWarnings("deprecation")
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == jb2 && e.getActionCommand().equals("停止生产")) {
                    dd.suspend();
                    ConsumerAndProducer.mark.setText("生产已停止");
                }
            }

        });
        jb1.addActionListener(new ActionListener() {
            @SuppressWarnings("deprecation")
            public void actionPerformed(ActionEvent e) {

                if (e.getSource() == jb1 && e.getActionCommand().equals("开始生产") && ConsumerAndProducer.index != 9) {
                    dd.resume();
                    ConsumerAndProducer.mark.setText("生产进行中");
                    return;
                }
                if (e.getSource() == jb1 && e.getActionCommand().equals("开始生产") && ConsumerAndProducer.index == 9) {
                    ConsumerAndProducer.jlabel.setText("仓库已满，生产停止，请先消费！");
                }
            }

        });

    }

    //以下为程序控制段方法
    public void paint(Graphics g) {
        if (x > getWidth()) {
            x = -50;
            if (ConsumerAndProducer.index > 7) {
                dd.suspend();
            }
            if (ConsumerAndProducer.mark1.getText() == "消费进行中") {
                Producer.dd1.resume();
            }
            Component[] labels = ConsumerAndProducer.jl5.getComponents();
            ((JLabel) labels[ConsumerAndProducer.index++]).setIcon(icon);
            if (ConsumerAndProducer.index == 9) {
                ConsumerAndProducer.jlabel.setText("仓库已满，生产停止，请先消费！");
            }
            if (ConsumerAndProducer.index != 9) {
                ConsumerAndProducer.jlabel.setText("生产状态下当前仓库产品数量为：" + ConsumerAndProducer.index);
            }
        }
        super.paint(g);
        g.drawImage(icon1, x, y, jl1);
        repaint();
    }
}


class Thread1 extends Thread {
    public void run() {
        while (true) {
            Producer.x += 10;
            try {
                Thread2.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}

class Thread2 extends Thread {
    public void run() {
        while (true) {
            Consumer.x += 25;
            try {
                Thread2.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }
}

class Thread3 extends Thread {
    private JFrame f;
    public Thread3(JFrame f) {
        this.f = f;
        JButton jb1 = new JButton("点击开始模拟");
        jb1.setBounds(2, 370, 150, 40);
        f.add(jb1);

        jb1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (ConsumerAndProducer.index == 0) {
                    Producer.dd1.start();
                    Consumer.dd.start();
                }
                if (ConsumerAndProducer.index != 0) {
                    ConsumerAndProducer.jlabel.setText("模拟进行中，请点击恢复模拟！");
                }
            }
        });

        JButton jb2 = new JButton("暂停模拟");
        jb2.setBounds(2, 412, 150, 40);
        f.add(jb2);
        jb2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ConsumerAndProducer.jlabel.setText("模拟已暂停，需恢复模拟请点击“恢复模拟”或者生产、消费按钮");
                Consumer.dd.suspend();
                Producer.dd1.suspend();
            }
        });

        JButton jb3 = new JButton("恢复模拟");
        jb3.setBounds(2, 456, 150, 40);
        f.add(jb3);
        jb3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //两个if消除Component数组越界问题
                if (ConsumerAndProducer.index == 9) {
                    ConsumerAndProducer.index = ConsumerAndProducer.index - 1;
                }
                if (ConsumerAndProducer.index == 0) {
                    ConsumerAndProducer.index = ConsumerAndProducer.index + 1;
                }
                Consumer.dd.resume();
                Producer.dd1.resume();
            }
        });
    }
}
