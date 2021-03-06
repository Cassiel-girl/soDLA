// Generated by ::pipe -m -bc  -rand none sdp2mcif_rd_req_src_pd_d1[78:0] (sdp2mcif_rd_req_src_valid_d1,sdp2mcif_rd_req_src_ready_d1) <= sdp2mcif_rd_req_src_pd_d0[78:0] (sdp2mcif_rd_req_src_valid_d0,sdp2mcif_rd_req_src_ready_d0)


package nvdla

import chisel3._


class NV_NVDLA_RT_pipe() extends Module {
    val io = IO(new Bundle {
        //general clock
        val nvdla_core_clk = Input(Clock())
        val nvdla_core_rstn = Input(Bool())

        //control signal
        val 


        //data signal
        val cacc2csb_resp_src_valid = Input(Bool()) /* data valid */
        val cacc2csb_resp_src_pd =  Input(UInt(34.W))  /* pkt_id_width=1 pkt_widths=33,33  */                                 /* pkt_id_width=1 pkt_widths=33,33  */


        val cacc2csb_resp_dst_valid = Output(Bool()) /* data valid */
        val cacc2csb_resp_dst_pd =  Output(UInt(34.W))  /* pkt_id_width=1 pkt_widths=33,33  */                                 /* pkt_id_width=1 pkt_widths=33,33  */


    })


    val cacc2csb_resp_pd_d  = Reg(Vec(conf.RT_CSB2CACC_LATENCY, UInt(34.W)))
    val cacc2csb_resp_valid_d = Reg(Vec(conf.RT_CSB2CACC_LATENCY, Bool()))
    val csb2cacc_req_pd_d= Reg(Vec(conf.RT_CSB2CACC_LATENCY, UInt(63.W)))
    val csb2cacc_req_pvld_d = Reg(Vec(conf.RT_CSB2CACC_LATENCY, Bool()))


    //assign port
    cacc2csb_resp_pd_d(0) := io.csb2cacc_req_src_pvld
    cacc2csb_resp_valid_d(0) := io.cacc2csb_resp_src_valid
    csb2cacc_req_pd_d(0) := csb2cacc_req_src_pd
    csb2cacc_req_pvld_d(0) := io.csb2cacc_req_src_pvld
    csb2cacc_req_src_prdy := true.B


    //initial condition
    withClockAndReset(io.nvdla_core_clk, !io.nvdla_core_rstn) {

        for(t <- 0 to (conf.RT_CSB2CACC_LATENCY-1){

            csb2cacc_req_pvld_d(t+1) := RegNext(csb2cacc_req_pvld_d(t))
            cacc2csb_resp_valid_d(t+1):= RegNext(cacc2csb_resp_valid_d(t))
  
        }
    } 

    //data flight
    withClock(io.nvdla_core_clk) {

        for(t <- 0 to (conf.RT_CSB2CACC_LATENCY-1){
            csb2cacc_req_pd_d(t+1) := RegNext(csb2cacc_req_pd_d(t))
            cacc2csb_resp_pd_d(t+1) := RegNext(cacc2csb_resp_pd_d(t))
        }
   
    }  

    //output assignment

    csb2cacc_req_dst_pvld:=csb2cacc_req_pvld_d(3)
    csb2cacc_req_dst_pd:=csb2cacc_req_pd_d(3)
    cacc2csb_resp_dst_valid:=cacc2csb_resp_valid_d(3)
    cacc2csb_resp_dst_pd:=cacc2csb_resp_pd_d(3)





  }